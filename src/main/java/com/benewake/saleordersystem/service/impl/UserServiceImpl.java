package com.benewake.saleordersystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.benewake.saleordersystem.entity.LoginTicket;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.mapper.LoginTicketMapper;
import com.benewake.saleordersystem.mapper.UserMapper;
import com.benewake.saleordersystem.service.UserService;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.CommonUtils;
import com.benewake.saleordersystem.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年06月30 13:42
 * 描 述： TODO
 */
@Service
public class UserServiceImpl implements UserService, BenewakeConstants {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;


    @Override
    public Result<Map<String, Object>> addUser(User user) {
        Map<String,Object> map = new HashMap<>();
        // 空处理 程序错误 抛出异常
        if(null == user){
            return Result.fail("用户信息不能为空！",null);//传入的user是空的
        }
        // 内容缺失
        if(StringUtils.isBlank(user.getUsername())){
            return Result.fail("用户名不能为空！",null);//传入的用户名是空的
        }
        if(StringUtils.isBlank(user.getPassword())){
            return Result.fail("密码不能为空!",null);//传入的密码是空的
        }
        if(user.getUserType()==null){
            return Result.fail("用户类型不能为空！",null);//传入的用户类型是空的
        }

        // 使用mybatis设置查询条件，验证用户名是否唯一
        QueryWrapper<User> queryWrap = new QueryWrapper<>();
        queryWrap.eq("FIM_user_name",user.getUsername());//依次对比传入的user的名字与FIM_user_name中的名字
        User u = userMapper.selectOne(queryWrap);//执行查询
        if(u != null){//已经存在同名用户
            return Result.fail("用户已存在！",null);
        }else{
            // 不存在的话，说明可以添加，生成一个随机盐值将密码与其结合后用MD5进行加密，之后存储在用户对象中
            user.setSalt(CommonUtils.generateUUID().substring(0, 5));
            user.setPassword(CommonUtils.md5(user.getPassword() + user.getSalt()));

            // 设置默认参数
            if(user.getUserConllection()==null){
                user.setUserConllection(0L);//检查用户收藏的字段是否为空，如果为空设置默认值为0
            }


            userMapper.insert(user);//调用userMapper中的方法
            return Result.success(map);
        }
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String,Object> map = new HashMap<>();

        //空值处理
        if (StringUtils.isBlank(username)) {
            map.put("error", "未注册无法登录，注册请飞书联系管理员！");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("error", "密码不能为空！");
            return map;
        }
        // 根据用户名查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("FIM_user_name",username);
        //上面两行是用mybatis-plus构建一个查询条件，条件是FIM_user_name字段等于输入的username
        User u = userMapper.selectOne(queryWrapper);//执行查询将结果存储在u中
        // 用户不存在
        if (null == u) {
            map.put("error", "未注册无法登录，注册请飞书联系管理员!");
            return map;
        }
        if(u.getUserType().equals(USER_TYPE_INVALID)){//检查返回的用户类型是否是无效的
            map.put("error","用户无效，请飞书联系管理员！");
            return map;
        }
        // 验证密码
        password = CommonUtils.md5(password + u.getSalt());//将密码和用户的盐值结合进行MD5加密处理赋值给密码
        if (!password.equals(u.getPassword())) {
            map.put("error", "密码错误！");
            return map;
        }

        // 上面流程走完之后说明登陆成功，生成登录凭证，存储在数据库中
        LoginTicket loginTicket = new LoginTicket();//这是生成的登陆凭证loginticket
        loginTicket.setUserId(u.getId());
        loginTicket.setTicket(CommonUtils.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + DEFAULT_EXPIRED_SECONDS*1000));
        // 持久化
        loginTicketMapper.insert(loginTicket);

        //将生成的登陆凭证添加到返回的map中，以及一些其他信息
        map.put("ticket",loginTicket.getTicket());
        map.put("userId",u.getId());
        map.put("username",u.getUsername());
        map.put("userType",u.getUserType());
        map.put("collection",u.getUserConllection());
        return map;

    }

    @Override
    public Map<String, Object> logout(String ticket) {
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(ticket)){//如果返回的名为ticket的cookie是空的
            map.put("ticketMessage","ticket不存在或已失效");
            return map;
        }
        // 条件查询，使用ticket的值作为条件查询数据库返回一个符合值的LoginTicket的对象也就是登陆凭证
        QueryWrapper<LoginTicket> qw = new QueryWrapper<>();
        qw.eq("FIM_ticket",ticket);
        LoginTicket loginTicket = loginTicketMapper.selectOne(qw);
        if(null == loginTicket || loginTicket.getStatus() != 0 || !loginTicket.getExpired().after(new Date())){//如果登陆凭证不存在或者是失效或者是过期，0表示有效状态
            map.put("ticketMessage","ticket不存在或已失效");
            return map;
        }
        loginTicket.setStatus(1);//如果前面的条件都通过，说明cookie有效，此时将状态码从0变为1
        loginTicketMapper.updateById(loginTicket);//将登陆凭证更新后的状态通过loginTicketMapper更新到数据库中
        map.put("ticketMessage","退出成功！");//将返回的map中设置信息，然后返回
        return map;
    }

    @Override
    public LoginTicket findLoginTicket(String ticket) {
        QueryWrapper<LoginTicket> qw = new QueryWrapper<>();
        qw.eq("FIM_ticket",ticket);
        LoginTicket loginTicket = loginTicketMapper.selectOne(qw);
        return loginTicket;
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public int updateUserType(Long id, Long type) {
        User u = userMapper.selectById(id);
        u.setUserType(type);
        return userMapper.updateById(u);
    }

    @Override
    public int updateUsername(Long id, String username) {
        User u = userMapper.selectById(id);
        u.setUsername(username);
        return userMapper.updateById(u);
    }

    @Override
    public int updatePassword(Long id, String password) {//修改密码
        User u = userMapper.selectById(id);//通过id调取用户
        if(u ==  null) {
            return -1;//找不到用户返回-1
        }
        // 加密存储
        u.setPassword(CommonUtils.md5(password+u.getSalt()));//找到之后，修改密码
        return userMapper.updateById(u);//在数据库中更新用户信息
    }

    @Override
    public List<User> getUsernameLikeList(String username,Long userType) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId,User::getUsername)
                .like(StringUtils.isNotBlank(username),User::getUsername,username)
                .eq(userType!=null,User::getUserType,userType);
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public User findSalesmanByName(String salesmanName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId)
                .eq(User::getUsername,salesmanName)
                .eq(User::getUserType,USER_TYPE_SALESMAN);
        return userMapper.selectOne(queryWrapper);
    }
}

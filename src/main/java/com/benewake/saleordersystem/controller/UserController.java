package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.LoginRequired;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.service.UserService;
import com.benewake.saleordersystem.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月07 15:29
 * 描 述： TODO
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController implements BenewakeConstants {
    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @ApiOperation("更新用户密码")
    @PostMapping("/updatePwd")
    @LoginRequired
    public Result<String> updatePassword(HttpServletRequest request, Map<String,Object> param){//param是请求体中包含的一种映射关系
        //通过param映射获取就收到的数据中对应的值，旧密码，新密码，重复验证新密码，用户id
        String oldPassword = (String) param.get("oldPassword");
        String newPassword = (String) param.get("newPassword");
        String rePassword = (String) param.get("rePassword");
        Long userId = Long.parseLong((String)param.get("userid"));
        // 判断空值
        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(rePassword)){//调用StringUtils中的函数进行判空
            return Result.fail("密码不能为空");
        }
        // 判断新密码两次输入是否一致
        if(!newPassword.equals(rePassword)){//判断两次输入的新密码是否一致
            return Result.fail("两次输入的密码不一致");
        }
        // 判断新密码是否符合条件

        // 判断旧密码是否正确(管理员不需要)
        User user = hostHolder.getUser();//从当前线程获取用户信息
        // 普通用户
        if(user.getUserType().equals(USER_TYPE_SALESMAN) || user.getUserType().equals(USER_TYPE_VISITOR)){//如果是销售员或者访客数据类型
            String password = CommonUtils.md5(oldPassword+user.getSalt());//传入的密码赋值给password
            if(!password.equals(user.getPassword())){//获取当前线程用户的密码与传入的进行匹配
                return Result.fail("旧密码错误,请输入正确的密码!",null);
            }
            // 修改密码
            userService.updatePassword(user.getId(),newPassword);//调用userService中updatePassword方法
            // 设置ticket过期 则重新登录
            String ticket = CookieUtil.getValue(request,"ticket");//获取当前的cookie
            userService.logout(ticket);//修改密码后将原有的cookie失效，调用logout退出方法
            return Result.success("修改成功,请重新登录!",null);
        }else if(user.getUserType().equals(USER_TYPE_ADMIN)){//如果当前数据类型是管理员
            // 空值处理
            if(userId == null){//直接判断当前接受的用户id
                return Result.fail("要修改的用户id为空",null);
            }
            // 管理员修改用户密码
            int res = userService.updatePassword(userId,newPassword);//调用修改密码方法，获取返回值
            if(res != -1){
                return Result.success("修改成功！",null);
            }else{
                return Result.fail("用户id不存在！",null);
            }
        }else if(user.getUserType().equals(USER_TYPE_SYSTEM)){
            // 无效 或 系统
            return Result.success("系统修改密码模块",null);
        }

        return Result.fail("无效用户！",null);
    }

    /**
     * 根据用户姓名模糊匹配
     * @return
     */
    @ApiOperation("根据姓名模糊匹配用户")
    @PostMapping("/likeList")
    public Result<List<User>> getUserLikeList(@RequestBody Map<String,Object> param){//从服务接受一个键盘的一个映射
        String username = (String) param.get("username");//通过映射得到用户名称
        Long userType = param.get("userType")==null?null:Long.parseLong((String) param.get("userType"));
        return Result.success(userService.getUsernameLikeList(username,userType));
    }

}

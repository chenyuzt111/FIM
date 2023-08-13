package com.benewake.saleordersystem.service;

import com.benewake.saleordersystem.entity.LoginTicket;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * @author Lcs
 */
public interface UserService {

    /**
     * 添加新的用户
     * @param user
     * @return
     */
    Result<Map<String,Object>> addUser(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Map<String,Object> login(String username,String password);

    /**
     * 退出登录状态
     * @param ticket
     * @return
     */
    Map<String,Object> logout(String ticket);

    /**
     * 获取登录凭证
     * @param ticket
     * @return
     */
    LoginTicket findLoginTicket(String ticket);

    /**
     * 根据用户Id查找用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 修改用户类型
     * @param id
     * @param type
     * @return
     */
    int updateUserType(Long id,Long type);

    /**
     * 更新用户名
     * @param id
     * @param username
     * @return
     */
    int updateUsername(Long id,String username);

    /**
     * 更新用户密码
     * @param id
     * @param password
     * @return
     */
    int updatePassword(Long id,String password);

    /**
     * 根据姓名模糊匹配用户
     * @param username
     * @return
     */
    List<User> getUsernameLikeList(String username,Long userType);

    /**
     * 根据姓名查找对应的销售员用户对象
     * @param salesmanName
     * @return
     */
    User findSalesmanByName(String salesmanName);


}

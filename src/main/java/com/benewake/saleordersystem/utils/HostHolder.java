package com.benewake.saleordersystem.utils;


import com.benewake.saleordersystem.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，用于代替session对象。
 * @author Lcs
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }//获取在之前setUser方法设置的用户对象

    public void clear(){
        users.remove();
    }

}

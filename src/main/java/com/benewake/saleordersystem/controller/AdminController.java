package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.AdminRequired;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.service.UserService;
import com.benewake.saleordersystem.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Lcs
 * @since 2023年07月06 15:34
 * 描 述： TODO
 */
@Api(tags = "管理员管理")
@ResponseBody
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * 添加新用户
     * @return
     */
    @ApiOperation("添加新用户接口")
    @AdminRequired
    @PostMapping("/add")//响应add路径请求
    public Result add(@RequestBody User user) {
        //System.out.println("新增用户信息："+user.toString());
        return userService.addUser(user);
    }


}

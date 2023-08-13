package com.benewake.saleordersystem.controller;

import com.benewake.saleordersystem.annotation.LoginRequired;
import com.benewake.saleordersystem.entity.User;
import com.benewake.saleordersystem.service.UserService;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.HostHolder;
import com.benewake.saleordersystem.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lcs
 * @since 2023年06月30 11:49
 * 描 述： TODO
 */
@Api(tags = "主页相关接口")
@RestController
@ResponseBody
public class HomeController implements BenewakeConstants {

    @Autowired //自动装配字段，将类的实例注入到这些字段中
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;//创建一个实例，此实例用于判断是否已有用户登录

    @Value("${server.servlet.context-path}") //将属性值注入到contextPath字段中
    private String contextPath;

    /**
     * 用户登录
     * @param response
     * @return 登录信息
     */
    @ApiOperation("用户登录接口")//文档描述
    @PostMapping("/login")//映射到路径为login的post请求
    public Result<Map<String,Object>> login(@RequestBody User user, HttpServletResponse response){//接受一个user对象，HttpServletResponse对象
        String username = user.getUsername();
        String password = user.getPassword();
        if(hostHolder.getUser() != null) {
            // 当前已存在登录用户
            Map<String,Object> map = new HashMap<>(1);
            // map.put("loginMessage","当前已有账号登录，请先退出当前账号！");
            return Result.fail(202,"当前已有账号登录，请先退出当前账号！",null);//Result中封装了返回的数据类型，是一个工具类
        }
        // 尝试登录
        Map<String,Object> map = userService.login(username,password);//调用了userService实例对象中的login方法，返回的是一个映射，具体的实现方法在userServiceimpl中看
        if (map.containsKey("ticket")) {//如果map中存在ticket键，也是就获得了登陆凭证
            //验证成功 设置Cookie并返回成功信息
            Cookie cookie = new Cookie("ticket", map.get("ticket").toString());//当验证登陆成功后需要设置一个名字为ticket的cookie
            cookie.setPath("/");
            cookie.setMaxAge(DEFAULT_EXPIRED_SECONDS);//设置cookie路径和日期
            response.addCookie(cookie);//将cookie添加到响应给服务器的响应对象HttpServletResponse response中
            return Result.success(200,"success",map);//返回一个成功的结果，附带成功的状态码，消息验证成功后返回的map,map里面存放着登陆凭证等信息
        }else{
            // 验证失败 返回失败信息 没有获得
            return Result.fail(400, (String) map.get("error"),null);
        }
    }

    /**
     * 用户登出
     * @param ticket
     * @return
     */
    @ApiOperation("用户登出接口")
    @GetMapping("/logout")//获取一个get请求，映射的路径是/logout
    public Result<Map<String,Object>> logout(@CookieValue("ticket") String ticket){//接受一个名为ticket的cookie值作为参数，通过@CookieValue注解来进行请求
        return Result.success(200, (String) userService.logout(ticket).get("ticketMessage"),null);
    }
}

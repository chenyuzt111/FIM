package com.benewake.saleordersystem.controller.intercepter;

import com.benewake.saleordersystem.annotation.AdminRequired;
import com.benewake.saleordersystem.annotation.SalesmanRequired;
import com.benewake.saleordersystem.utils.BenewakeConstants;
import com.benewake.saleordersystem.utils.CommonUtils;
import com.benewake.saleordersystem.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Lcs
 * @since 2023年07月06 15:35
 * 描 述： TODO
 */
@Component
public class SalesmanRequiredInterceptor implements HandlerInterceptor, BenewakeConstants {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截的为方法时
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            SalesmanRequired salesmanRequired = method.getAnnotation(SalesmanRequired.class);
            // 有标记 且此时处于未登录状态
            Long type;
            if(null != salesmanRequired && (hostHolder.getUser() == null || (type = hostHolder.getUser().getUserType()).equals(USER_TYPE_VISITOR)
                    || type.equals(USER_TYPE_INVALID))){
                // 提示账号未登录
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(CommonUtils.getJSONString(1,"您的权限不够！"));
                writer.close();
                return false;
            }
        }

        return true;
    }
}

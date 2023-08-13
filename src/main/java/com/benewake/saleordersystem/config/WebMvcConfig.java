package com.benewake.saleordersystem.config;

import com.benewake.saleordersystem.annotation.AdminRequired;
import com.benewake.saleordersystem.controller.intercepter.AdminRequiredInterceptor;
import com.benewake.saleordersystem.controller.intercepter.LoginRequiredInterceptor;
import com.benewake.saleordersystem.controller.intercepter.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Lcs
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    private AdminRequiredInterceptor adminRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这里的顺序决定了拦截器的执行顺序
        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.jpeg");
        registry.addInterceptor(loginRequiredInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.jpeg");
        registry.addInterceptor(adminRequiredInterceptor)
                .excludePathPatterns("/**/*.css","/**/*.js","/**/*.png","/**/*.jpg","/**/*.jpeg");

    }
}

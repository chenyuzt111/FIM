package com.benewake.saleordersystem.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lcs
 * 描 述： 日志记录
 * @since 2023年06月29 15:11
 */

@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * ..表示包及子包 该方法代表controller层所有方法
     */
    @Pointcut("execution(public * com.benewake.saleordersystem.controller.*.*(..))")
    public void controllerMethod(){}

    @Around("controllerMethod()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        // before
        // 用户XXX，在XXX（时间），访问了[xxx方法]
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        String now = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date());
        String target = joinPoint.getSignature().getDeclaringTypeName();
        log.info(String.format("用户[%s],在[%s],访问了[%s]的[%s]方法，参数为[%s].",
                ip,now,target,joinPoint.getSignature().getName(),Arrays.asList(joinPoint.getArgs())));

        Object result = joinPoint.proceed();

        // after
        //log.info("返回结果："+JSON.toJSONString(result));

        return result;

    }



}

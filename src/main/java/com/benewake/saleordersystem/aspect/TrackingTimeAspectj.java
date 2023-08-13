package com.benewake.saleordersystem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Lcs
 * @since 2023年07月27 09:32
 * 描 述： 用于记录有TrackingTIme注解的方法的执行时间
 */
@Component
@Slf4j
@Aspect
public class TrackingTimeAspectj {

    @Pointcut("@annotation(com.benewake.saleordersystem.annotation.TrackingTime)")
    public void trackingTimePointCut(){}

    @Around("trackingTimePointCut()")
    public Object trackingTimeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toLongString();
        //log.info( "Start execute method (" + methodName + ") at "+ (new Date()).toString());
        Object result = joinPoint.proceed();
        log.info("Call method (" + methodName + ") used time:" + (System.currentTimeMillis() - startTime)+"ms");
        return result;
    }



}

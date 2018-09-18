package com.aop;

import com.dbconfig.ChooseDataSourceUtils;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class AopConfig {

    @Before("execution(* com.service..*.*(..))")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name);
        ChooseDataSourceUtils.setChooseType("master");
    }
}

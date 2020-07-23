package com.zyy.seckill.plugin;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SpringAop {

    @Pointcut("execution(* com.zyy.seckill.service.*.*(..))")
    private void pointcut(){

    }

    @Before("pointcut()")
    private void before(){
        System.out.println("befor");
    }

    @After("pointcut()")
    private void after(){
        System.out.println("after");
    }
}

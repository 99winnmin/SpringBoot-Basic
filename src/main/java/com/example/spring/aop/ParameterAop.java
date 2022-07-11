package com.example.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // AOP로 동작하게함, log를 남기게하는 것
@Component
public class ParameterAop {

    // execution안에 들어가는 것은 내가 만든 객체들 경로인듯?
    @Pointcut("execution(* com.example.spring.controller..*.*(..))")
    private void pointCut(){

    }

//    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("======before() AOP=======");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName()); // 메서드 이름 log 찍기

        Object[] args = joinPoint.getArgs();

        for(Object obj : args){
            System.out.println("type : "+obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }

//    @AfterReturning(value = "pointCut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("======afterReturn() AOP=======");
        System.out.println("return obj : "+returnObj);
    }


}

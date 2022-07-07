package com.example.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    // aop 하위에 있는 메서드들에만 logging함
    @Pointcut("execution(* com.example.spring.controller..*.*(..))")
    private void cut(){}

    // Timer annotation이 설정된 메서드만 logging함
    @Pointcut("@annotation(com.example.spring.annotation.Timer)")
    private void enableTimer(){}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("======around() AOP=======");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());
    }
}

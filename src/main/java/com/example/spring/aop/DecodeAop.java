package com.example.spring.aop;

import com.example.spring.UserAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.spring.controller..*.*(..))")
    private void cut(){}


    @Pointcut("@annotation(com.example.spring.annotation.Decode)")
    private void enableDecode(){}


    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        // json으로 들어올때 복호문이니까 평문으로 decoding해서 logging
        for(Object arg : args){
            if(arg instanceof UserAop){
                UserAop userAop = UserAop.class.cast(arg);
                String base64Email = userAop.getEmail();
                // decoding
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8");
                userAop.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        // return 해줄 때는 평문으로 되있던 것을 복호화해서 return
        if(returnObj instanceof UserAop){
            UserAop userAop = UserAop.class.cast(returnObj);
            String email = userAop.getEmail();
            // encoding
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            userAop.setEmail(base64Email);
        }
    }
}

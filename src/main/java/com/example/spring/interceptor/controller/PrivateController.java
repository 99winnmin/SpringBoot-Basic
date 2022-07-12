package com.example.spring.interceptor.controller;

import com.example.spring.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
@Auth
@Slf4j
public class PrivateController { // 내부 사용자 혹은 세션이 인증된 사용자만 사용

    // @Auth 특정 메서드에 걸어줄 수 도 있지만 이런 방식은 유지보수가 어렵기 때문에
    // controller에 걸어주거나 특정 url에 매칭시켜주는것이 좋음
    @GetMapping("/hello")
    public String hello(){
        log.info("private hello controller");
        return "private hello";
    }
}

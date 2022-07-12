package com.example.spring.interceptor.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicController { // Open API, 아무나 사용가능

    @GetMapping("/hello")
    public String hello(){
        return "public hello";
    }
}

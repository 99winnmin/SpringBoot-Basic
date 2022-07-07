package com.example.spring.controller;

import com.example.spring.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }

    // ResponseEntity
    // 보통 pageController에서는 responseBody를 안보내는게 일반적임
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        var user = new User(); // 타입 추론 자동으로 하게 var 사용
        user.setName("RYU");
        user.setAddress("서울");
        return user;
    }
}

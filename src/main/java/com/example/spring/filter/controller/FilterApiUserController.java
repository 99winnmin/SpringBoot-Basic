package com.example.spring.filter.controller;

import com.example.spring.filter.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // logging을 위한 annotation
@RestController
@RequestMapping("/api/temp")
public class FilterApiUserController {

    @PostMapping("/post")
    public User user(@RequestBody User user){
        log.info("Temp : {}",user); // {}안에 객체의 toString()이 매칭이됨
        return user;
    }
}

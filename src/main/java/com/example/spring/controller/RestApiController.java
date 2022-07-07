package com.example.spring.controller;

import com.example.spring.UserAop;
import com.example.spring.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id+" "+name;
    }

    @PostMapping("/post")
    public UserAop post(@RequestBody UserAop userAop){
        return userAop;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        // db logic
        Thread.sleep(1000*2);
    }

    @Decode
    @PutMapping("/put")
    public UserAop put(@RequestBody UserAop userAop){
        System.out.println("putt");
        System.out.println(userAop);
        return userAop;
    }
}

package com.example.spring.aop;

import com.example.spring.UserAop;
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
}

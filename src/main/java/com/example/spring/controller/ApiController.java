package com.example.spring.controller;

import com.example.spring.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //해당 class는 rest api 처리하는 controller
@RequestMapping("/api") //requestMapping uri를 지정해주는 annotation
public class ApiController {

    @GetMapping("/hello") // http://localhost:9090/api/hello
    public String hello(){
        return  "안녕하세요 저는 유승민입니다";
    }
    // text 리턴하는 방식인데 이런 경우는 거의 없음
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // JSON response
    // req -> object mapper -> object -> method -> object -> object mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user; // 200 OK
    }

    // ResponseEntity를 사용해서 status 설정하기
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

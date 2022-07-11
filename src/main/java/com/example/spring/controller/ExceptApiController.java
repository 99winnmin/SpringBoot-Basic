package com.example.spring.controller;

import com.example.spring.dto.ExceptionUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ExceptApiController {

    // required = false 는 requestParam이 없어도 되지만 null이 들어가게됨됨
   @GetMapping("/except/get")
    public ExceptionUser get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        ExceptionUser exceptionUser = new ExceptionUser();
        exceptionUser.setName(name);
        exceptionUser.setAge(age);

        int a = 10+age;

        return exceptionUser;
    }

    @PostMapping("/except/post")
    public ExceptionUser post(@Valid @RequestBody ExceptionUser exceptionUser){
        System.out.println(exceptionUser);
        return exceptionUser;
    }

    // Controller 안에 ExceptionHandler를 코딩하게 되면 이 class 안에서 발생하는 예외에만 관여하게됨
    // GlobalHandler 보다 우선순위가 높아서 여기서 예외처리하고 끝
   @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
       System.out.println("api controller");
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

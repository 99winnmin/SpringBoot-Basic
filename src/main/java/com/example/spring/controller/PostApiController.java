package com.example.spring.controller;

import com.example.spring.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){
        //GET에서와 마찬가지로 Map을 쓰면 key가 무엇인지 알기 힘듬! 직접 찍어봐야 알 수 있음
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }

    //★★★★★★★★현업에서 쓰는 방식★★★★★★★★
    @PostMapping("/post02")
    public void post(@RequestBody PostRequestDto requestData){
        System.out.println(requestData);
    }
}

package com.example.spring.controller;

import com.example.spring.dto.PostRequestDto;
import com.example.spring.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto requestDto, @PathVariable(name = "userId") Long id){
        System.out.println(id);
        System.out.println(requestDto);
        return requestDto;
    }
}

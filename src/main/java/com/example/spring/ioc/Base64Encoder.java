package com.example.spring.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base64") // spring에 권한을 넘긴 것, 이름을 지정할 수 있음
public class Base64Encoder implements IEncoder{
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}

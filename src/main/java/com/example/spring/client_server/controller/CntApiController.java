package com.example.spring.client_server.controller;

import com.example.spring.client_server.dto.Req;
import com.example.spring.client_server.dto.UserResponse;
import com.example.spring.client_server.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class CntApiController {

    private final RestTemplateService restTemplateService;

    public CntApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/get")
    public UserResponse getHello(){
        return restTemplateService.get();
    }

    @PostMapping("/post")
    public UserResponse postHello(){
        return restTemplateService.post();
    }

    @PostMapping("/post/exchange")
    public UserResponse exchangeHello(){
        return restTemplateService.exchange();
    }

    @PostMapping("/post/generic-exchange")
    public Req<UserResponse> genericExchangeHello(){
        return restTemplateService.genericExchange();
    }
}

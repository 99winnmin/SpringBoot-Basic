package com.example.spring.async.controller;

import com.example.spring.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncApiController {

    private final AsyncService asyncService;

    public AsyncApiController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/hello")
    public CompletableFuture hello(){
        log.info("completable future init");
        return asyncService.run();
    }
}

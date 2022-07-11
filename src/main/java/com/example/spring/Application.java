package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Base64;

@SpringBootApplication
@ServletComponentScan // 특정 class에만 filter를 적용시키고 싶을 때
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //System.out.println(Base64.getEncoder().encodeToString("ojysep9987@gmail.com".getBytes()));
    }

}

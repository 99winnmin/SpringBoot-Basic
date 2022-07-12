package com.example.spring.client_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse { // json 규격에 따라 class 작성
    private String name;
    private int age;
}

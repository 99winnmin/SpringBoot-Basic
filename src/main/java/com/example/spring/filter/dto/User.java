package com.example.spring.filter.dto;

import lombok.*;

// @Getter, @Setter -> Lombok에서 활용해서 getter, setter 자동생성해주는 annotation
@Data // getter,setter, equals, toString 등등 전부 만들어주는 annotation
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 객체 변수를 받는 생성자도 생성
public class User {
    private String name;
    private int age;
}

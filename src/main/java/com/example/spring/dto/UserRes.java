package com.example.spring.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    @ApiModelProperty(value = "사용자의 이름", example = "ryu", required = true)
    private String name;

    @ApiModelProperty(value = "사용자의 나이", example = "24", required = true)
    private int age;
}

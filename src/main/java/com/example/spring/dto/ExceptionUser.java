package com.example.spring.dto;

import javax.validation.constraints.*;

public class ExceptionUser {
    @NotEmpty
    @Size(min = 1, max = 10)
    private String name;

    @Min(1)
    @NotNull
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ExceptionUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

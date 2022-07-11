package com.example.spring.dto;

import com.example.spring.annotation.YearMonth;
import objectMapperDto.Car;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ValidationUser {
    @NotBlank
    private String name;

    @Min(value = 0)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. xxx-xxxx-xxxx")
    private String phoneNumber;

    // pattern에 yyyyMMdd 매개변수로 던짐, annotation에서 default로 정해줄 수 도 있음음
    @YearMonth(pattern = "yyyyMMdd")
    private String reqYearMonth; // yyyyMM

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }

    // 그러나 한 클래스에 이렇게 만든다면 이런 형식이 필요한 곳에 재사용이 안됨됨
   /*@AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")
    public boolean isReqYearMonthValidation(){
        System.out.println("assert True call");
        try{
            LocalDate localDate = LocalDate.parse(this.reqYearMonth+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        }catch (Exception e){
            return false;
        }
        return true;
    }*/

    @Override
    public String toString() {
        return "ValidationUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                '}';
    }
}

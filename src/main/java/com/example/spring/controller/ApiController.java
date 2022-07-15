package com.example.spring.controller;

import com.example.spring.dto.User;
import com.example.spring.dto.UserReq;
import com.example.spring.dto.UserRes;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 Controller"}) // 화면에 보이는 이름 재설정
@RestController //해당 class는 rest api 처리하는 controller
@RequestMapping("/api") //requestMapping uri를 지정해주는 annotation
public class ApiController {

    @GetMapping("/hello") // http://localhost:9090/api/hello
    public String hello(){
        return  "안녕하세요 저는 유승민입니다";
    }
    // text 리턴하는 방식인데 이런 경우는 거의 없음
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    // JSON response
    // req -> object mapper -> object -> method -> object -> object mapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user; // 200 OK
    }

    // ResponseEntity를 사용해서 status 설정하기
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // ======================================================================
    // swagger 활용 부분
    /*@GetMapping("/plus/{x}") // 그러나 @ApiParam(value = "x값") 이렇게 붙여주는건 다소 불편할 수 있음
    public int plus(
            @ApiParam(value = "x값")
            @PathVariable int x,

            @ApiParam(value = "y값")
            @RequestParam int y){
        return x + y;
    }*/

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y값", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int apiUpgradePlus(@PathVariable int x, @RequestParam int y){
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때")
    @ApiOperation(value = "사용자의 이름과 나이를 echo하는 메소드")
    @GetMapping("/swagger-user")
    public UserRes user(UserReq userReq){
        return new UserRes(userReq.getName(), userReq.getAge());
    }

    @PostMapping("/swagger-post")
    public UserRes postUser(@RequestBody UserReq req){
        return new UserRes(req.getName(), req.getAge());
    }
}

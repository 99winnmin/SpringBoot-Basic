package com.example.spring.controller;

import com.example.spring.dto.ValidationUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ValidApiController {
    // @Valid을 붙여서 유효성 검사하겠다!
    // BindingResult는 코드에서 직접 예외처리하는 방법!
    @PostMapping("/validation/user")
    public ResponseEntity validationUser(@Valid @RequestBody ValidationUser validationUser,
                                                 BindingResult bindingResult){
        // 유효성 검사에서 에러났을 때 해당 에러 출력하기
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                // 어디서 에러 났는지 key값 알아내기
                System.out.println("field : " + field.getField());
                // 유효성 에러 메세지 출력
               System.out.println(message);
               sb.append("field : "+field.getField());
               sb.append("message : "+message);

            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        System.out.println(validationUser);

        // 코드에서 예외처리하고 Logic 관련 코드가 실행되게함

       return ResponseEntity.ok(validationUser);
    }
}

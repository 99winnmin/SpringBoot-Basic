package com.example.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("=================================");

        // Text JSON -> Object
        // Object -> Text JSON

        // controller req json(text) -> object
        // response object -> json(text)

        // controller외에 객체를 json으로 바꿔야한다던지 할 때에 objectMapper 활용
        var objectMapper = new ObjectMapper();

        // object -> text(json)
        // object mapper가 getter를 활용한다.
        var user = new User("ryu",24,"010-9899-8415");
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text -> object
        // object mapper는 default 생성자를 필요로 한다.
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);


    }

}

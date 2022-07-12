package com.example.spring.client_server.service;

import com.example.spring.client_server.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost:9091/api/server/get
    // response 받아옴
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9091")
                .path("/api/server/get")
                .queryParam("name","aaaaaa")
                .queryParam("age",99999)
                .encode()
                .build()
                .toUri(); // 데이터를 받아올 서버의 url을 만드는 부분
        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        // http GET 방식으로 String Object를 가져오겠다 : getForObject
//        String result = restTemplate.getForObject(uri, String.class);
        // http GET 방식으로 Entity(데이터)를 가져옴, entity에 담겨져 있는 것이 많음
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}

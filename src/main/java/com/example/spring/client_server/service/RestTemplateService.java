package com.example.spring.client_server.service;

import com.example.spring.client_server.dto.Req;
import com.example.spring.client_server.dto.UserRequest;
import com.example.spring.client_server.dto.UserResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost:9091/api/server/get
    // response 받아옴
    public UserResponse get(){
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

    public UserResponse post(){
        // http://localhost:9091/api/server/post/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9091")
                .path("/api/server/post/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri(); // expand는 pathVar에 순서대로 매칭됨
        System.out.println(uri);

        // http body -> object -> object mapper -> json -> rest template -> http body json
        // 위 과정이 자동으로 이루어짐
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        // response를 뭘로 받을 지 설정
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public UserResponse exchange(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9091")
                .path("/api/server/post/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        System.out.println(uri);

        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","ffffff")
                .body(req); // request header, body 직접 만들기

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
        return response.getBody();
    }

    public Req<UserResponse> genericExchange(){ // json이 재사용될 때!!
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9091")
                .path("/api/server/post/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100,"steve")
                .toUri();
        System.out.println(uri);

        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<UserRequest>();
        req.setHeader(
                new Req.Header()
        );
        req.setResBody(
                userRequest
        );

        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization","abcd")
                .header("custom-header","ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response = restTemplate
                .exchange(requestEntity, new ParameterizedTypeReference<>(){});

        // response.getBody() -> ResponseEntity의 내용 추출
        // response.getBody().getrBody() -> ResponseEntity를 넘어서 Req<UserResponse> 내용 추출
       return response.getBody();
    }
}

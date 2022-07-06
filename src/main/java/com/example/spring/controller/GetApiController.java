package com.example.spring.controller;

import com.example.spring.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") //http://localhost:9090/api/get/hello
    public String getHello(){
        return "get Hello";
    }

    //옛날 방식
    @RequestMapping(path = "/hi", method = RequestMethod.GET) //get/post/put/delete 방식 다 동작함
    public String hi(){
        return "hi";
    }

    //http://localhost:9090/api/get/path-variable/{name}
    //경로 변수의 이름과 메서드 안에서 사용하는 변수 이름이 같아야한다
    //그러나 이미 사용하고 있는 이름이라면 다음과 같이 바꿔줄 수 있다
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name="name") String pathName){
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    // 쿼리 파라미터 사용해보기기
   // search?q=intelliJ&rlz=1C1PNKB_enKR998KR999
    // &sxsrf = ALiCzsZlq_mnb_a7yPo1bcj9mW2iuR6Qlw%3A1657116005179
    // &ei = ZZXFYuO0CvLX2roPiNWWgAc
    // &ved = 0ahUKEwij-9uWtuT4AhXyq1YBHYiqBXAQ4dUDCA4
    // &uact = 5
    // &oq = intelliJ
    // &gs_lcp = Cgdnd3Mtd2l6EAMyBAgjECcyCggAEIAEEIcCEBQyCggAEIAEEIcCEBQyCwgAEIAEELEDEIMBMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyCggAELEDEIMBEEM6BwgjELADECc6BwgAEEcQsAM6CwguEIAEEMcBENEDOggIABCABBCxAzoRCC4QgAQQsQMQgwEQxwEQ0QM6BAgAEENKBAhBGABKBAhGGABQ3ghY5hlgvxtoA3ABeACAAZwBiAG1CJIBAzAuOJgBAKABAcgBCsABAQ
    // &sclient = gws-wiz

    // ?key=value & key2 = value2

    // http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=24
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        //그러나 Map을 쓸 경우 key가 무엇인지 알 수 없음
        StringBuilder sb = new StringBuilder();

        //람다식 이용
        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });

        return sb.toString();
    }

    //위의 방식에 비해 명시적임임 그러나 key가 늘어날때 마다 추가해줘야함
    //현업에서 쓰는 방식은 아님
    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ){
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);

        return name+" "+email+" "+age;
    }

    //★★★★★★★★현업에서 쓰는 방식★★★★★★★★
    //객체를 받아서 쓰는 방식이 가장 일반적임!!
    @GetMapping("query-param03")
    public String queryParam03(UserRequest userRequest){
        //springboot에서 query-param 메서드 매개변수에 객체가 들어오게 되면
        //?user=steve&email=steve@gmail.com&age=24 이 값들을 springboot가 판단하게됨
        //변수와 키를 자동으로 매칭해줌
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getAge());

        return userRequest.toString();
    }


}

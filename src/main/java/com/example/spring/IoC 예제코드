package com.example.spring;

import com.example.spring.ioc.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Spring Container에서 관리되는 객체들을 bean이라고 부름, 특정 클래스를 new 생성한게 bean이다?
// 즉, Spring에서 객체를 직접 관리하는데 관리되는 것들을 bean이라고 부르고 bean들이 관리되고 있는 곳이 spring container임
// spring container가 제어하는 권한을 가져갔기 때문에 제어의 역전이고 이것을 ioc라고 부름
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        /*Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
        Encoder encoder = new Encoder(base64Encoder);
        String result = encoder.encode(url);
        System.out.println(result);

        encoder.setIEncoder(urlEncoder);
        result = encoder.encode(url);

        System.out.println(result);*/

        // Encoder까지 spring에 @Component로 권한을 넘겼기 때문에 spring으로부터 주입을 받게 됨
        // 개발자가 직접 new 사용하는 코드가 없어짐, 모든 권한이 spring으로 넘어갔다!
        Encoder encoder = context.getBean(Encoder.class);
        Encoder02 encoder02 = context.getBean("urlEncode", Encoder02.class);

        String result = encoder.encode(url);
        System.out.println(result);

        String result02 = encoder02.encode(url);
        System.out.println(result02);

    }

}

@Configuration
class AppConfig{
    // class를 bean으로 등록안하고 bean으로 사용하는 방법
    @Bean("base64Encode")
    public Encoder02 encoder(Base64Encoder base64Encoder){
        return new Encoder02(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder02 encoder(UrlEncoder urlEncoder){
        return new Encoder02(urlEncoder);
    }
}
package com.example.spring.config;

import com.example.spring.interceptor.interceptorPack.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor // final로 선언된 객체들을 생성자로 주입받을 수 있도록 함
public class MvcConfig implements WebMvcConfigurer {
    // @Autowired 로도 받을 수 있지만 순환 참조가 일어날 수 도 있음
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns으로 특정 url에 대해서만 AuthInterceptor를 동작하게 할 수 있음, 필요한 주소들을 여러개 추가할 수도 있음
        // registry.addInterceptor(authInterceptor).excludePathPatterns("/api/private/*"); 다음과 같이 제외할 수도 있음
        // 여러가지 interceptor가 동작할 수 있는데 그것은 코딩된 순서대로 동작함 -> 인증과정 depth 구현가능!
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
    }
}

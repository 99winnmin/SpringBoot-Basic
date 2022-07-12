package com.example.spring.interceptor.interceptorPack;

import com.example.spring.annotation.Auth;
import com.example.spring.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 원래대로라면 filter에서와 했던 것과 마찬가지로 ContentCachingRequestWrapper로 형변환하고
        // doFilter메서드를 실행해야함(request의 커서가 다 이동되서 읽을 내용이 없어지는 이슈를 해결하기 위함)
        // filter에서 전처리 -> interceptor에서 값을 받아서 형변환해야함
        // 그렇지 않으면 HttpServletRequest를 interceptor에서 형변환해줄 수 없음
        // 하지만 이 코드에서는 일단은 filter의 전처리 없이 그냥 진행
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build().toUri(); // uri 에서 query 파싱

        log.info("request : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 나의 서버는 모두 public으로 동작을 하는데
        // 단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키등을 검사함
        if(hasAnnotation){ // 우리가 원하는 annotation으로 처리하는 방식
            // 권한체크
            String query = uri.getQuery(); // 쿼리가 같으면 통과? 보통은 쿠키나 세션을 검사함
            log.info("query : {}", query);
            if(query.equals("name=steve")){
                return true;
            }
            throw new AuthException(); // 권한이 없음을 예외처리
            // return false;
        }

        return true; // true가 되어야 interceptor를 통과할 수 있음음
    }
    private boolean checkAnnotation(Object handler, Class clazz){
        // resource(js, html)은 무조건 통과
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;
            // annotation이 달려있는지 check
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation이 있을 때는 true
            return true;
        }

        return false;
    }
}

package com.example.spring.filter.filterPack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

//@Component // spring에 등록하는 방식, 모든 controller에 동작하게됨
@Slf4j
@WebFilter(urlPatterns = "/api/filter/post/*") // 특정 class에만 filter를 적용시키고 싶을 때, urls : 여러개를 설정할 수 도 있음
public class GlobalFilter implements Filter { // javax.servlet.* 상속
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        // ContentCaching : 내용을 미리 담고 커서가 이동하면서 읽음 -> 실질적인 데이터의 커서가 이동x
        // 하지만 맨처음에는 길이기반으로 초기화되고 ContentCachingRequestWrapper 내부에서 read,write하는 부분은 따로 있음음
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        // doFilter를 통해 내부로 들어가 메서드가 실행되고 그 결과 content를 읽을 수 있음
        // 따라서 doFilter 이후에 log를 찍어봐야함!!!!
        chain.doFilter(httpServletRequest, httpServletResponse);

        String url = httpServletRequest.getRequestURI();

        // 후처리
        // 위에서 읽은 내용을 logging 할 수 있음
       String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request : {}, requestBody : {}",url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        // getContentAsByteArray()로 다 읽어버려서 body의 커서가 끝까지 내려감...
        // 따라서 읽은만큼 복사해줘야 client가 제대로된 response를 받을 수 있음
        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody : {}",httpStatus, resContent);

    }
}

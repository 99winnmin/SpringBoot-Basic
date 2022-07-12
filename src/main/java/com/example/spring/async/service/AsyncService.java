package com.example.spring.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    // CompletableFuture는 다수 개의 api를 동시에 전송받아서 join한 다음
    // 거기에 대한 결과를 모아서 return 할 때 쓰는게 좋음
    // 그러나 해당 코드방식은 spring에서 정한 thread를 사용(8개 thread가 default)

    @Async("async-thread") // "async-thread" : 직접 설정한 thread
    public CompletableFuture run(){
        // hello();를 호출해도 한 클래스에 존재하는 메서드는 async를 타지 않음
        return new AsyncResult(hello()).completable();
    }// Async는 AOP기반이기 때문에 proxy 패턴을 타서 public 메서드에만 사용할 수 있음

    public String hello() {
        for (int i=0 ; i<10 ; i++){
            try {
                Thread.sleep(2000);
                log.info("Thread sleep...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return "async hello";
    }
}

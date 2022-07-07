package com.example.spring.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component //개발자가 따로 설정할 것 없이 spring에서 모든 생명주기가 관리됨
public class Encoder {
    private IEncoder iEncoder;
    // spring에 권한을 넘긴 순간 2개(Base64Encoder,UrlEncoder)가 있기 때문에 spring에서 어떤 걸 매칭해줘야 할지 모름
    // @Qualifier를 통해 매칭할 것을 지정 해줌
    public Encoder(@Qualifier("base64")IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}

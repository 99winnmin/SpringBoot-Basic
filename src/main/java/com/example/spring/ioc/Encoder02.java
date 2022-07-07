package com.example.spring.ioc;


public class Encoder02 {
    private IEncoder iEncoder;

    public Encoder02(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public void setIEncoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}

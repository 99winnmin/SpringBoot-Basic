package ioc;

import java.util.Base64;

public class Encoder {
    private IEncoder iEncoder;
    // Encoder 객체 입장에서 IEncoder라는 객체를 외부에서
    // 주입받았기 때문에 DI를 받은 것!
    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}

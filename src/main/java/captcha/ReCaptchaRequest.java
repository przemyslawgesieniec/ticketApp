package main.java.captcha;

import lombok.Builder;

import java.net.URI;

@Builder
public class ReCaptchaRequest {

    private String secret;
    private String response;
    private String remoteIp;
    private String googleUrl;

    public URI getVerifyURI(){
        return URI.create(String.format("%s?secret=%s&response=%s&remoteip=%s",googleUrl,secret,response,remoteIp));
    }

}

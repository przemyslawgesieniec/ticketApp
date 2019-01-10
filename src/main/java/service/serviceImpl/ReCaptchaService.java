package main.java.service.serviceImpl;

import main.java.config.recapcha.CaptchaConfiguration;
import main.java.captcha.GoogleResponse;
import main.java.captcha.ReCaptchaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ReCaptchaService {

    @Autowired
    private CaptchaConfiguration captchaConfiguration;

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public Boolean verifyResponse(String response) {

        RestTemplate restTemplate = new RestTemplate();

        ReCaptchaRequest reCaptchaRequest = ReCaptchaRequest
                .builder()
                .response(response)
                .secret(captchaConfiguration.getSecret())
                .googleUrl(GOOGLE_RECAPTCHA_VERIFY_URL)
                .build();

        GoogleResponse googleResponse = restTemplate.getForObject(reCaptchaRequest.getVerifyURI(),GoogleResponse.class);
        return Optional.ofNullable(googleResponse.isSuccess()).orElse(false);
    }

}

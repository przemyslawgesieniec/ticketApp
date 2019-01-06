package main.java.config.recapcha;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource({"classpath:application.properties"})
public class CaptchaConfiguration {

    @Value("${google.recaptcha.key.site}")
    private String site;

    @Value("${google.recaptcha.key.secret}")
    private String secret;
}

package main.java.captcha;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "success",
        "challenge_ts",
        "hostname",
})
@Getter
public class GoogleResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("challenge_ts")
    private String challengeTs;

    @JsonProperty("hostname")
    private String hostname;

}
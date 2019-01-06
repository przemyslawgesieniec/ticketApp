package main.java.dto;

import lombok.Builder;
import lombok.Data;
import main.java.validator.constraintAnnotations.ValidEmail;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserDto {

    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    private String password;

    private String reCaptcha;
}

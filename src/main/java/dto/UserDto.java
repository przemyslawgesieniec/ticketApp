package main.java.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

    private String name;
    private String lastName;
    private String email;
    private String password;
}

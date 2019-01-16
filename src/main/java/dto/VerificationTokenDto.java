package main.java.dto;

import lombok.Builder;
import lombok.Data;
import main.java.entity.UserEntity;

import java.util.Date;

@Builder
@Data
public class VerificationTokenDto {

    private String token;
    private UserEntity user;
    private Date expiryDate;
}

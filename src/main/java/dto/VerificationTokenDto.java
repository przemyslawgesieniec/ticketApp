package main.java.dto;

import lombok.Builder;
import lombok.Data;
import main.java.model.entity.User;

import java.util.Date;

@Builder
@Data
public class VerificationTokenDto {

    private String token;
    private User user;
    private Date expiryDate;
}

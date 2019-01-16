package main.java.service;

import main.java.dto.UserDto;
import main.java.entity.VerificationTokenEntity;

import java.util.Optional;

public interface UserVerificationService {

    VerificationTokenEntity generateAndPersistToken(UserDto user);

    Optional<UserDto> validateToken(String token);

}

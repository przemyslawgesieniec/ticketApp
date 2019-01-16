package main.java.service;

import main.java.dto.UserDto;
import main.java.entity.User;
import main.java.entity.VerificationToken;

import java.util.Optional;

public interface UserVerificationService {

    VerificationToken generateAndPersistToken(UserDto user);

    Optional<UserDto> validateToken(String token);

}

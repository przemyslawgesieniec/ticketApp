package main.java.service;

import main.java.dto.UserDto;
import main.java.model.entity.User;
import main.java.model.entity.VerificationToken;

import java.util.Optional;

public interface UserVerificationService {

    VerificationToken generateAndPersistToken(User email);

    Optional<UserDto> validateToken(String token);

}

package main.java.service;

import main.java.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService  extends UserDetailsService {

    Optional<UserDto> findByEmail(String email);
    UserDto getUserByEmail(String email);
    UserDto save(UserDto registrationRequestParams);
    void activateUser(String email);
}

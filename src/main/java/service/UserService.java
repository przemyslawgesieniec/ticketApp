package main.java.service;

import main.java.dto.UserDto;
import main.java.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService  extends UserDetailsService {

    Optional<User> findByEmail(String email);

    User save(UserDto registrationRequestParams);
}

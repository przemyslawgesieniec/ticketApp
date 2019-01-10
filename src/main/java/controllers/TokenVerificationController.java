package main.java.controllers;

import main.java.dto.UserDto;
import main.java.service.UserService;
import main.java.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TokenVerificationController {

    @Autowired
    UserVerificationService userVerificationService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/registrationConfirmation", method = RequestMethod.GET)
    public String tokenVerification(@RequestParam String token) {

        Optional<UserDto> userDto = userVerificationService.validateToken(token);
        if(userDto.isPresent()) {
            userService.activateUser(userDto.get().getEmail());
            return "registrationComplete";
        }
        return "registrationComplitionFailed";
    }
}



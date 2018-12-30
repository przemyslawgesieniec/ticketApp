package main.java.controllers;

import main.java.dto.UserDto;
import main.java.model.entity.User;
import main.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class RegistrationController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserDto user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationForm(ModelAndView modelAndView, UserDto user, BindingResult bindingResult, HttpServletRequest request) {

        Optional<User> registeredUser = userService.findByEmail(user.getEmail());

        if (registeredUser.isPresent()) {
            bindingResult.rejectValue("email", null, "User with this email already exists");
            return "register";
        }
        userService.save(user);
        return "login";
    }

}

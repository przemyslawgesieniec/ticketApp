package main.java.controllers;

import main.java.dto.UserDto;
import main.java.model.entity.User;
import main.java.service.UserService;
import main.java.service.serviceImpl.ReCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegistrationController {


    @Autowired
    UserService userService;

    @Autowired
    private ReCaptchaService captchaService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, UserDto user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    // Process form input data
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(@ModelAttribute("user") @Valid UserDto user, BindingResult bindingResult, HttpServletRequest request) {

        //TODO: create registration service and move logic there

        Optional<User> registeredUser = userService.findByEmail(user.getEmail());

        if (registeredUser.isPresent()) {
            bindingResult.rejectValue("email", null, "User with this email already exists");
        }

        boolean isCaptchaValid = captchaService.verifyResponse(request.getParameter("g-recaptcha-response"));

        if (!isCaptchaValid) {
            bindingResult.rejectValue("reCaptcha", null, "Captcha validation fails");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register","user", user);
        }

        userService.save(user);
        return new ModelAndView("login","user", user);
    }

}

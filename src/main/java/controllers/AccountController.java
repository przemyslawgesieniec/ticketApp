package main.java.controllers;

import main.java.dto.UserDto;
import main.java.service.serviceImpl.EventServiceImpl;
import main.java.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ModelAndView getUserAccountInformation(ModelAndView modelAndView, HttpServletRequest request) {

        UserDto loggedInUser = userService.getUserByEmail(request.getUserPrincipal().getName());

        modelAndView.setViewName("account");
        modelAndView.addObject("user", loggedInUser);

        return modelAndView;
    }
}

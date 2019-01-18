package main.java.controllers;

import main.java.dto.EventDto;
import main.java.dto.UserDto;
import main.java.service.UserService;
import main.java.service.serviceImpl.EventServiceImpl;
import main.java.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ModelAndView getUserAccountInformation(ModelAndView modelAndView, HttpServletRequest request) {

        UserDto loggedInUser = userService.getUserByEmail(request.getUserPrincipal().getName());
        List<EventDto> requestedTickets = userService.getAllRequestedTickets(loggedInUser);
        List<EventDto> boughtTickets = userService.getAllBoughtTickets(loggedInUser);
        List<EventDto> rejectedTickets = userService.getAllRejectedTickets(loggedInUser);

        modelAndView.setViewName("account");
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("requestedTickets", requestedTickets);
        modelAndView.addObject("boughtTickets", boughtTickets);
        modelAndView.addObject("rejectedTickets", rejectedTickets);
        modelAndView.addObject("role", "user");

        return modelAndView;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView getAdminAccountInformation(ModelAndView modelAndView, HttpServletRequest request) {

        UserDto loggedInUser = userService.getUserByEmail(request.getUserPrincipal().getName());
        List<UserDto> users = userService.getAllUsers();
//        List<EventDto> ticketsRequestedByUsers = userService.getAllTicketsRequestedByUsers();
        List<EventDto> boughtTickets = userService.getAllBoughtTickets(loggedInUser);
//
        modelAndView.setViewName("account");
        modelAndView.addObject("user", loggedInUser);
        modelAndView.addObject("role", "admin");
        modelAndView.addObject("boughtTickets", boughtTickets);
        modelAndView.addObject("users", users);
//        modelAndView.addObject("requestedTickets", ticketsRequestedByUsers);

        return modelAndView;
    }

//    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
//    public ModelAndView manageUsers(ModelAndView modelAndView, HttpServletRequest request) {
//
//    }

}

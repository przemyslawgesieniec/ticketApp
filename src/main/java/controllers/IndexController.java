package main.java.controllers;

import main.java.dto.EventDto;
import main.java.service.UserService;
import main.java.service.serviceImpl.EventServiceImpl;
import main.java.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ModelAndView getUserIndex(ModelAndView modelAndView, HttpServletRequest request) {

        return getModelAndView(modelAndView);
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public ModelAndView getAdminIndex(ModelAndView modelAndView) {

        List<EventDto> eventDtoList = eventService.getAllEvents();
        modelAndView.setViewName("index");
        modelAndView.addObject("eventList", eventDtoList);
        modelAndView.addObject("role", "admin");


        return modelAndView;
    }

    @RequestMapping(value = {"/user/requestTicket"}, method = RequestMethod.GET)
    public ModelAndView requestTicket(ModelAndView modelAndView,
                                      @RequestParam(value = "id") Long eventId,
                                      Principal principal) {
//        userService.requestEvent(eventId,principal.getName());
        return getModelAndView(modelAndView);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView) {
        List<EventDto> eventDtoList = eventService.getAllEvents();
        modelAndView.setViewName("index");
        modelAndView.addObject("eventList", eventDtoList);
        modelAndView.addObject("role", "user");
        return modelAndView;
    }
}

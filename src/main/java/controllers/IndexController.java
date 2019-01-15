package main.java.controllers;

import main.java.dto.EventDto;
import main.java.service.serviceImpl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    EventServiceImpl eventService;

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ModelAndView getUserIndex(ModelAndView modelAndView) {

        List<EventDto> eventDtoList = eventService.getAllEvents();
        modelAndView.setViewName("index");
        modelAndView.addObject("eventList", eventDtoList);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String getAdminIndex(Model model) {

        model.addAttribute("name", "admin");
        return "index";
    }
}

package main.java.controllers;

import com.itextpdf.text.DocumentException;
import main.java.dto.UserEventDto;
import main.java.service.Invoice;
import main.java.dto.EventDto;
import main.java.service.UserService;
import main.java.service.serviceImpl.EmailServiceImpl;
import main.java.service.serviceImpl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    UserService userService;

    @Autowired
    Invoice invoice;

    @Autowired
    EmailServiceImpl emailService;



    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public ModelAndView getUserIndex(ModelAndView modelAndView) {
        modelAndView.addObject("role", "user");
        getModelAndView(modelAndView);
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
        final boolean isRequested = eventService.requestEvent(eventId, principal.getName());
        UserEventDto userEventDto = eventService.getUserEventDtoByUserAndEvent(principal.getName(),eventId);
        EventDto eventDto = eventService.getById(eventId);


        modelAndView.addObject("role", "user");
        String message = "You already have this ticket";
        if(isRequested){
            message = "Ticket is requested";
            try {
                emailService.sendInvoice(principal.getName(),userEventDto,eventDto.getName(),eventDto.getCost());
            } catch (FileNotFoundException | DocumentException | MessagingException e) {
                message = "Ticket is requested, but invoice wan not sent";
            }
        }
        modelAndView.addObject("isRequested", message);
        return getModelAndView(modelAndView);
    }

    @RequestMapping(value = {"/admin/buyTicket"}, method = RequestMethod.GET)
    public ModelAndView buyTicket(ModelAndView modelAndView,
                                  @RequestParam(value = "id") Long eventId,
                                  Principal principal) {
        eventService.buyTicket(eventId, principal.getName());
        modelAndView.addObject("role", "admin");
        modelAndView.addObject("isBought", "You have bought the ticket");
        return getModelAndView(modelAndView);
    }

    private ModelAndView getModelAndView(ModelAndView modelAndView) {
        List<EventDto> eventDtoList = eventService.getAllEvents();
        modelAndView.setViewName("index");
        modelAndView.addObject("eventList", eventDtoList);
        return modelAndView;
    }

}

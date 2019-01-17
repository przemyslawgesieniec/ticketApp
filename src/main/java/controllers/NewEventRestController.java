package main.java.controllers;

import main.java.repository.EventRepository;
import main.java.service.serviceImpl.EventServiceImpl;
import main.java.view.NewEventInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
public class NewEventRestController {

    @Autowired
    EventServiceImpl eventService;

    @RequestMapping("index/admin/addNewEvent")
    String addNewEvent(@RequestBody NewEventInput eventInput) {

        try {
            eventService.addNewEvent(eventInput);
        } catch (ParseException e) {
            return "Form data has some errors";
        }
        return "New event created";
    }
}

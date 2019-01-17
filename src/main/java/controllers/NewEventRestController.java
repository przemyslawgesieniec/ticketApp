package main.java.controllers;

import main.java.view.NewEventInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class NewEventRestController {

    @RequestMapping("index/admin/addNewEvent")
    String addNewEvent(@RequestBody NewEventInput eventInput){

        System.out.println("dizała");
        System.out.println(eventInput);

        return "ok";
    }
}

package main.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value={"/user"}, method = RequestMethod.GET)
    public String getUserIndex(Model model) {

        model.addAttribute("name", "user");
        return "index";
    }

    @RequestMapping(value={"/admin"}, method = RequestMethod.GET)
    public String getAdminIndex(Model model) {

        model.addAttribute("name", "admin");
        return "index";
    }
}

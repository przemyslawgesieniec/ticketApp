package main.java.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/index/admin/";
        }
        return "redirect:/index/user/";
    }

    @RequestMapping("/welcome")
    public String welcomeResolver() {
        return "welcome";
    }
}

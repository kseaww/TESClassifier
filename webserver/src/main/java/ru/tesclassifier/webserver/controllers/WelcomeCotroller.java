package ru.tesclassifier.webserver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeCotroller {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model) {
        return "Welcome";
    }
}

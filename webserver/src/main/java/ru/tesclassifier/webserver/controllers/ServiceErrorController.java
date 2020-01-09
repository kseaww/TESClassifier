package ru.tesclassifier.webserver.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServiceErrorController implements ErrorController  {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        switch (statusCode) {
            case 404:
                return "NotFoundPage";
            default:
                return "ErrorPage";
        }
    }
    
    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}

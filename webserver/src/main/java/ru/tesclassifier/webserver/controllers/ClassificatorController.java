package ru.tesclassifier.webserver.controllers;

import ru.tesclassifier.webserver.models.ClassificatorForm;
import ru.tesclassifier.webserver.services.ClassificatorService;
import ru.tesclassifier.webserver.services.TextClasses;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClassificatorController {
    
    private ClassificatorService classificatorService;
    
    public ClassificatorController() throws Exception {
        classificatorService = new ClassificatorService();
    }
    
    @RequestMapping(value = "/classificator", method = RequestMethod.GET)
    public String classifyForm(Model model) {
        model.addAttribute("classificatorForm", new ClassificatorForm());
        return "ClassificatorForm";
    }
    
    @RequestMapping(value = "/classificator", method = RequestMethod.POST)
    public String classifySubmit(@ModelAttribute ClassificatorForm classificatorForm, Model model) throws Exception {
        if (classificatorForm.getText().trim().isEmpty()) {
            return "ClassificatorInvalidInput";
        }
        
        TextClasses textClass = classificatorService.getClass(classificatorForm.getText());
        model.addAttribute("textClass", TextClasses.getTextClassRusName(textClass));
        return "ClassificatorResult";
    }
}

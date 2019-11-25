package ru.tesclassifier.webserver.controllers;

import ru.tesclassifier.webserver.services.LibraryService;
import ru.tesclassifier.webserver.models.TextClassName;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LibraryController {
    
    private LibraryService libraryService;
    
    public LibraryController() throws Exception {
        libraryService = new LibraryService();
    }
    
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public String libraryAll(Model model) {
        model.addAttribute("textClassNames", libraryService.getTextClasses());
        return "LibraryMain";
    }
    
    @RequestMapping(value = "/library/section/{className}", method = RequestMethod.GET)
    public String librarySection(Model model, @PathVariable("className") String className) {
        TextClassName[] textClassNames = libraryService.getTextClasses();
        for (int i = 0; i < textClassNames.length; ++i) {
            if (textClassNames[i].getClassName().equals(className)) {
                model.addAttribute("classNameRus", textClassNames[i].getRusName());
                break;
            }
        }
        
        model.addAttribute("className", className);
        model.addAttribute("articles", libraryService.getArticles(className));
        return "LibrarySection";
    }
    
    @RequestMapping(value = "/library/section/{className}/file/{fileName}", method = RequestMethod.GET)
    public String librarySectionFile(
            Model model,
            @PathVariable("className") String className,
            @PathVariable("fileName") String fileName) throws Exception {
        
        String articleTitle = fileName.substring(0, fileName.length() - 4);
        model.addAttribute("articleTitle", articleTitle);
        model.addAttribute("articleText", libraryService.getFileContent(className, fileName));
        return "LibrarySectionFile";
    }
}

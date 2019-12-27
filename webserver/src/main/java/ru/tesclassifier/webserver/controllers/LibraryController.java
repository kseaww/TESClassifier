package ru.tesclassifier.webserver.controllers;

import java.util.ArrayList;

import ru.tesclassifier.webserver.services.LibraryService;
import ru.tesclassifier.webserver.models.TextClassInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tesclassifier.webserver.services.TextClasses;

@Controller
public class LibraryController {
    
    private LibraryService libraryService;
    
    public LibraryController() throws Exception {
        libraryService = new LibraryService();
    }
    
    @RequestMapping(value = "/library", method = RequestMethod.GET)
    public String libraryAll(Model model) {
        TextClassInfo[] textClassInfos =  libraryService.getTextClassInfos();
        model.addAttribute("textClassInfos", textClassInfos);
        
        int allArticleAmount = 0;
        for (int i = 0; i < textClassInfos.length; ++i) {
            allArticleAmount += textClassInfos[i].getArticlesAmount();
        }
        model.addAttribute("allArticlesAmount", allArticleAmount);
        
        return "LibraryMain";
    }
    
    @RequestMapping(value = "/library/section/{className}", method = RequestMethod.GET)
    public String librarySection(Model model, @PathVariable("className") String className) {
        for (int i = 0; i < TextClasses.values().length; ++i) {
            TextClasses currentTextClasses = TextClasses.values()[i];
            if (currentTextClasses.toString().equals(className)) {
                model.addAttribute("classNameRus", TextClasses.getTextClassRusName(currentTextClasses));
                break;
            }
        }
        
        model.addAttribute("className", className);
        ArrayList<String> articles = libraryService.getArticles(className);
        model.addAttribute("articles", articles);
        model.addAttribute("articlesAmount", articles.size());
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

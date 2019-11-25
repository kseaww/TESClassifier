package ru.tesclassifier.webserver.services;

import ru.tesclassifier.webserver.models.TextClassName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class LibraryService {
    
    public TextClassName[] getTextClasses() {
        TextClassName[] textClassNames = new TextClassName[TextClasses.values().length];
        
        for (int i = 0; i < textClassNames.length; ++i) {
            textClassNames[i] = new TextClassName();
            textClassNames[i].setClassName(TextClasses.values()[i].toString());
            textClassNames[i].setRusName(TextClasses.getTextClassRusName(TextClasses.values()[i]));
        }
        
        return textClassNames;
    }
    
    public ArrayList<String> getArticles(String className) {
        File folder = new File("data" + "\\" + className);
        File[] listOfFiles = folder.listFiles();

        ArrayList<String> fileNames = new ArrayList<String>();
        for (int i = 0; i < listOfFiles.length; ++i) {
            fileNames.add(listOfFiles[i].getName());
        }

        return fileNames;
    }
    
    public String getFileContent(String className, String fileName) throws Exception {
        File file = new File("data" + "\\" + className + "\\" + fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(file);
        
        String content = document.getElementsByTagName("text").item(0).getTextContent();
        
        return content;
    }
}

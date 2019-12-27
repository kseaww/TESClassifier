package ru.tesclassifier.webserver.services;

import ru.tesclassifier.webserver.models.TextClassInfo;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class LibraryService {
    
    public TextClassInfo[] getTextClassInfos() {
        TextClassInfo[] textClassNames = new TextClassInfo[TextClasses.values().length];
        
        for (int i = 0; i < textClassNames.length; ++i) {
            textClassNames[i] = new TextClassInfo();
            textClassNames[i].setClassName(TextClasses.values()[i].toString());
            textClassNames[i].setRusName(TextClasses.getTextClassRusName(TextClasses.values()[i]));
            
            File folder = new File("data" + "\\" + TextClasses.values()[i].toString());
            textClassNames[i].setArticlesAmount(folder.listFiles().length);
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

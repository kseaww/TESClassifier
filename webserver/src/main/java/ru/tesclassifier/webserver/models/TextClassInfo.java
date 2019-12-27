package ru.tesclassifier.webserver.models;

public class TextClassInfo {
    private String className;
    private String rusName;
    private int articlesAmount;
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }
    
    public int getArticlesAmount() {
        return articlesAmount;
    }

    public void setArticlesAmount(int articlesAmount) {
        this.articlesAmount = articlesAmount;
    }
}

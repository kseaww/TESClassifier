package ru.tesclassifier.webserver.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ClassificatorService {
    private Classifier classifier;
    private HashMap<String, Integer> attributiveWords;
    private ArrayList<String> wordFilter;
    
    public ClassificatorService() throws Exception {
        classifier = (Classifier) SerializationHelper.read(new FileInputStream("TEStext.model"));
        
        attributiveWords = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("TESwordvector.txt"), "UTF8"));
        int wordCounter = 0;
        String line = reader.readLine();
        while (line != null) {
            attributiveWords.put(line, wordCounter);
            ++wordCounter;
            line = reader.readLine();
        }
        
        wordFilter = new ArrayList();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("TESwordfilter.txt"), "UTF8"));
        line = reader.readLine();
        while (line != null) {
            wordFilter.add(line);
            line = reader.readLine();
        }
    }
    
    public TextClasses getClass(String text) throws Exception {
        Instances vectorizedText = vectorizeText(getWords(text));
        
        double textClass = classifier.classifyInstance(vectorizedText.firstInstance());
        
        return TextClasses.values()[(int)textClass];
    }
    
    private Instances vectorizeText(ArrayList<String> words) {
        double[] vector = new double[attributiveWords.size() + 1];
        
        for (int i = 0; i < vector.length; ++i) {
            vector[i] = 0;
        }
        
        for (int i = 0; i < words.size(); ++i) {
            Integer attributeIndex = attributiveWords.get(words.get(i));
            if (attributeIndex != null) {
                vector[attributeIndex] = vector[attributeIndex] + 1;
            }
        }
        
        ArrayList<String> textClasses = new ArrayList<>();
        for (int i = 0; i < TextClasses.values().length; ++i) {
            textClasses.add(TextClasses.values()[i].toString());
        }
        Attribute classAttribute = new Attribute("class", textClasses);
        
        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < attributiveWords.size(); ++i) {
            attributes.add(new Attribute("attribute_word_" + (i + 1)));
        }
        attributes.add(classAttribute);
        
        Instances vectorizedText;
        vectorizedText = new Instances("text", attributes, 0);
        vectorizedText.setClass(classAttribute);
        vectorizedText.add(new DenseInstance(1.0, vector));
        vectorizedText.instance(0).setClassMissing();
        return vectorizedText;
    }
    
    private ArrayList<String> getWords(String text) {
        String[] uncheckedWords = text.split("\\s+");
        
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < uncheckedWords.length; ++i) {
            String checkedWord = uncheckedWords[i].toLowerCase().replaceAll("[.,\\/#!\\?$%\\^&\\*;:{}=_`~()]", "");
            if (!(checkedWord.isEmpty() || wordFilter.contains(checkedWord))) {
                words.add(checkedWord);
            }
        }
        
        return words;
    }
}

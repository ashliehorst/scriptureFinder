/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashlie
 */
public class Entry {
    
    Scripture scripture;
    private String content;
    private String date;
    private String topics;   
    private List<Scripture> scriptureList;
    private List<String> topicList;
    
    public Entry() {
        topicList = new ArrayList<>();
        scriptureList = new ArrayList<>(); 
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getContent() {
        return content;
    }
    
    public List<Scripture> getScriptureList() {
        return scriptureList;
    }
    
    public List<String> getTopicList() {
        return topicList;     
    }
    
    public void addTopic(String topic) {
         topicList.add(topic);
    }
    
    public void addScripture(Scripture scripture) {
        scriptureList.add(scripture);
    }
    
    public void displayScriptureList(List<Scripture> script) {
       for (Scripture scripture : script) {
         System.out.println("Scripture: " + scripture.getBook() + 
            " " + scripture.getChapter());
        }
    }
    
    public void displayTopicList(List<String> top) {
       for (String topic : top) {        
         System.out.println("Topic: " + topic);
        }
    }
}

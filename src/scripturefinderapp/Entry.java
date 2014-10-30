/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.util.ArrayList;
import java.util.List;

/**
 * ENTRY CLASS
 * @author Ashlie
 */
public class Entry {
    
    Scripture scripture;
    private String content;
    private String date;  
    private List<Scripture> scriptureList;
    private List<String> topicList;
    
    /**
     * ENTRY CONSTRUCTOR
     * 
     */
    public Entry() {
        topicList = new ArrayList<>();
        scriptureList = new ArrayList<>(); 
    }
    
    /**
     * SET CONTENT
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * SET DATE
     * 
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    /**
     * GET DATE
     * 
     * @return 
     */
    public String getDate() {
        return date;
    }
    
    /**
     * GET CONTENT
     * 
     * @return 
     */
    public String getContent() {
        return content;
    }
    
    /**
     * GET SCRIPTURE LIST
     * 
     * @return 
     */
    public List<Scripture> getScriptureList() {
        return scriptureList;
    }
    
    /**
     * GET TOPIC LIST
     * 
     * @return 
     */
    public List<String> getTopicList() {
        return topicList;     
    }
    
    /**
     * ADD TOPIC
     * 
     * @param topic
     */
    public void addTopic(String topic) {
         topicList.add(topic);
    }
    
    /**
     * ADD SCRIPTURE
     * 
     * @param scripture
     */
    public void addScripture(Scripture scripture) {
        scriptureList.add(scripture);
    }
    
    /**
     * DISPLAY SCRIPTURE LIST
     * 
     * @param script
     */
    public void displayScriptureList(List<Scripture> script) {
       for (Scripture scripture : script) {
         System.out.println("Scripture: " + scripture.getBook() + 
            " " + scripture.getChapter());
        }
    }
    
    /**
     * DISPLAY TOPIC LIST
     * 
     * @param top
     */
    public void displayTopicList(List<String> top) {
       for (String topic : top) {        
         System.out.println("Topic: " + topic);
        }
    }
}

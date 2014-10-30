/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.util.ArrayList;
import java.util.List;

/**
 * SCRIPTURE CLASS
 * @author Ashlie
 */
public class Scripture {
    
    private String book;
    private String chapter;
    private String startVerse;
    private String endVerse;
    private List<String> scriptureList;
    Scripture scripture;
    
    /********************
    * SCRIPTURE CONSTRUCTOR
     ************/
    public Scripture() {
         scriptureList = new ArrayList<>(); 
    }
    
    /********************
    * ADD SCRIPTURE
     ************/
     public void addScripture(String scripture) {
       scriptureList.add(scripture);
    }
    
    /********************
    * GET SCRIPTURE LIST
     ************/
    public Scripture getScriptureList() {
        return scripture;
    }
    
    /********************
    * GET BOOK
     ************/
    public String getBook () {
        return book;
    }
     
    /********************
    * SET BOOK
     ************/
    public void setBook(String book) {
        this.book = book;     
    }
    
    /********************
    * GET CHAPTER
     ************/
    public String getChapter() {
        return chapter; 
    }
    
    /********************
    * SET CHAPTER
     ************/
    public void setChapter(String chapter) {
       this.chapter = chapter;
    }
    
    /********************
    * GET START VERSE
     ************/
    public String getStartVerse() { 
        return startVerse;
    }
    
    /********************
    * SET START VERSE
     ************/
    public void setStartVerse(String verse) {
        this.startVerse = verse;      
    }
    
    /********************
    * GET END VERSE
     ************/
    public String getEndVerse() { 
        return endVerse;
    }
    
    /********************
    * SET END VERSE
     ************/
    public void setEndVerse(String verse) {
        this.endVerse = verse;
    }   
}

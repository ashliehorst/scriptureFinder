/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ashlie
 */
public class Scripture {
    
    private String book;
    private String chapter;
    private String startVerse;
    private String endVerse;
    private List<String> scriptureList;
    Scripture scripture;
    
    public Scripture() {
         scriptureList = new ArrayList<>(); 
    }
    
    public Scripture(String book, int chapter, int verse) {
            
    }
    
    public Scripture(String book, int chapter) {
        
    }
    
     public void addScripture(String scripture) {
       scriptureList.add(scripture);
    }
    
    public Scripture getScriptureList() {
        return scripture;
    }
    
    public String getBook () {
        return book;
    }
            
    public void setBook(String book) {
       // if (validBook(x)) {
        this.book = book;
      //  }
       // else {
       //     System.out.println("Error: Not valid book"); 
       // }       
    }
    
    public boolean validBook(String x) {
        return true;
    }
    
    public String getChapter() {
        return chapter; 
    }
    
    public void setChapter(String chapter) {
      //  if (x < 0) {
       this.chapter = chapter;
       // }
       // else {
       //     System.out.println("Error: Not valid chapter number");  
       // }
    }
    
    public String getStartVerse() { 
        return startVerse;
    }
    
    public void setStartVerse(String verse) {
       // if (x < 0) {
        this.startVerse = verse;
      //  }
       // else {
       //     System.out.println("Error: Not valid verse number");
      //  }
       
    }
    
     public String getEndVerse() { 
        return endVerse;
    }
    
    public void setEndVerse(String verse) {
        this.endVerse = verse;
    }
    
}

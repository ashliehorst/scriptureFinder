/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * THREADS CLASS
 * @author Ashlie
 */
public class Threads implements Runnable {
    public Label myLabel;
    public Label myLabel2;
    public Label myLabel3;
    public int countEntries;
    public int countScriptures;
    public String scriptures;
    public int countTopic;
    
    /********************
    * RUN
    * Count the instances of entry, scripture, and topic
     ************/
    @Override
    public void run() {
        try {
        int i = 0; 
        int g = 0;
        int t = 0;
        
        // Count the entries
        for ( i = 0; i < countEntries;i++){
           Thread.sleep(1000);       
           update(i, g, t);     
        }
       
        // Count the scriptures
        for (g = 0; g < countScriptures; g++){
           Thread.sleep(1000);
           update(i, g, t);        
        }

        // Count the topics
        for (t = 0; t < countTopic;t++){
           Thread.sleep(1000);
           update(i, g, t);
        }
       
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /********************
    * UPDATE
    * Update the numbers to the screen
     ************/
    public void update(int countEntries, int countScriptures, int countTopic){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                myLabel.setText("Entries found: " + countEntries);
                myLabel2.setText("Scriptures found: " + countScriptures);
                myLabel3.setText("Topics found: " + countTopic);
            }
        });
        
    }
}
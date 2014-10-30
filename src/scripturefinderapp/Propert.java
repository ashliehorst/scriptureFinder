/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PROPERTY CLASS
 * @author Ashlie
 */
public class Propert {  
    
    String propertiesFile = "/resources/prop.properties";
    
    /********************
    * GET TOPIC FILE
     ************/
     public String getTopicFile() {
	Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream(propertiesFile));
        } catch (IOException ex) {
            Logger.getLogger(Propert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String topicFile = prop.getProperty("terms");
        return topicFile;
     }
     
     /********************
    * GET SCRIPTURE FILE
     ************/
     public String getScriptureFile() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getResourceAsStream(propertiesFile));
        } catch (IOException ex) {
            Logger.getLogger(Propert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String scriptureFile = prop.getProperty("scriptures");
        return scriptureFile;
     }     
}

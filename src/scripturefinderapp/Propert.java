/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Ashlie
 */
public class Propert {  
    
    String propertiesFile = "/resources/prop.properties";
    
     public String getTopicFile() throws IOException{
	Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream(propertiesFile));
        
        String topicFile = prop.getProperty("terms");
        return topicFile;
     }
     
     public String getScriptureFile() throws IOException {
        Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream(propertiesFile));
        
        String scriptureFile = prop.getProperty("scriptures");
        return scriptureFile;
     }     
}

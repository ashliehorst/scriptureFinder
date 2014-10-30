/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ashlie
 */
public class ReadFile {

    /*********************
     * READ FILE
     * @param journal 
     */
    public void readFile(Journal journal, String fXmlFile) {
      try {
	//File fXmlFile = new File("src/scripturefinderapp/XMLDoc.xml");
       // File fXmlFile = new File("src/scripturefinderapp/inputFile.xml");
       // File fXmlFile = new File("src/scripturefinderapp/inputFile.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        GridPane grid = new GridPane();
	//optional, but recommended (deals with spacing)
	doc.getDocumentElement().normalize();
 
        // System.out.println("Loading File: " + doc.getDocumentURI());
        
        // This is the "journal" tag: doc.getDocumentElement().getNodeName()
        // System.out.println("Journal: ");
              
        String book;
        String chapter;
        String startverse;
        String endverse;
        String topic;
        String content;
        String date;
        int entryCount = 0;
        int scriptureCount = 0;
        int topicCount = 0;

        // nList is the entry element
	NodeList entryList = doc.getElementsByTagName("entry");
 
	for (int temp = 0; temp < entryList.getLength(); temp++) {          
            // nNode is the entry element at element 0, 1, 2, 3, etc.
            Node entryElement = entryList.item(temp);
            Element eElement = (Element) entryElement; 
            // Make a new entry 
            Entry entry = new Entry(); 
            // ERROR @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
            // System.out.println("Entries loaded: " + ++entryCount);
            
            if (entryElement.getNodeType() == Node.ELEMENT_NODE) {             
                // Make date and content strings              
                date = eElement.getAttribute("date");                
                entry.setDate(date);  
                
                // For loop for scriptures
                NodeList scripList = eElement.getElementsByTagName("scripture");
                
                for (int script = 0; script < scripList.getLength(); script++){
                    Scripture scripture = new Scripture();   
                    Node scriptureElement = scripList.item(script);
                    Element scriptureE = (Element) scriptureElement; 
            
                    book = scriptureE.getAttribute("book");
                    chapter = scriptureE.getAttribute("chapter");   
                    
                    scripture.setBook(book);
                    scripture.setChapter(chapter);
                 
                    if (scriptureE.hasAttribute("startverse")) {
                        startverse = scriptureE.getAttribute("startverse");
                        scripture.setStartVerse(startverse);
                    }
                    if (scriptureE.hasAttribute("endverse")) {
                       endverse = scriptureE.getAttribute("endverse");
                       scripture.setEndVerse(endverse); 
                    }
                    
                    // Add object to scripture list
                    entry.addScripture(scripture);
                    // ERROR @@@@@@@@@@@@@@@@@@@@@@@@@@@
                    // System.out.println("Scriptures loaded: " + ++scriptureCount);       
                }
              
                // For loop for topics
                NodeList topList = eElement.getElementsByTagName("topic"); 
                for (int top = 0; top < topList.getLength(); top++){                   
                    Node topElement = topList.item(top);
                    Element topicE = (Element) topElement; 
                    
                    topic = topicE.getTextContent();  
                    // TEST  System.out.println("topic:" + topic);
                    
                    // Add topic to the list of topics in entry
                    entry.addTopic(topic);
                    
                    // ERROR @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                    // Label topicLabel = new Label("Topics loaded: " + ++topicCount);
                    // grid.add(topicLabel, 5, 0, 1, 1);
                    // System.out.println("Topics loaded: " + ++topicCount);
                }

                // Get the content
                content = eElement.getElementsByTagName("content").item(0).getTextContent(); 
                
                // Deal with the whitespace 
                content = content.trim();
                content = content.replaceAll("\\n\\s+", "\n");
                    
                // Set the content
            //  TEST  System.out.println("content:" + content);
                entry.setContent(content); 
           }                   
            // Add the entry object to the list in journal
           journal.addEntry(entry);               
        } 
	        
    } catch (Exception e) {
	e.printStackTrace();
    }
    }
}
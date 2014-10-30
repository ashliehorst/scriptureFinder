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
import javafx.scene.layout.GridPane;

/**
 * READ FILE CLASS
 * @author Ashlie
 */
public class ReadFile {

    /*********************
     * READ FILE
     * @param journal 
     */
    public void readFile(Journal journal, String fXmlFile) {
      try {
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        GridPane grid = new GridPane();
	//optional, but recommended (deals with spacing)
	doc.getDocumentElement().normalize();
              
        String book;
        String chapter;
        String startverse;
        String endverse;
        String topic;
        String content;
        String date;

        // nList is the entry element
	NodeList entryList = doc.getElementsByTagName("entry");
 
	for (int temp = 0; temp < entryList.getLength(); temp++) {          
            // nNode is the entry element at element 0, 1, 2, 3, etc.
            Node entryElement = entryList.item(temp);
            Element eElement = (Element) entryElement; 
            // Make a new entry 
            Entry entry = new Entry(); 
            
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
                }
              
                // For loop for topics
                NodeList topList = eElement.getElementsByTagName("topic"); 
                for (int top = 0; top < topList.getLength(); top++){                   
                    Node topElement = topList.item(top);
                    Element topicE = (Element) topElement; 
                    
                    topic = topicE.getTextContent();  
                    
                    // Add topic to the list of topics in entry
                    entry.addTopic(topic);
                }

                // Get the content
                content = eElement.getElementsByTagName("content").item(0).getTextContent(); 
                
                // Deal with the whitespace 
                content = content.trim();
                content = content.replaceAll("\\n\\s+", "\n");
                    
                // Set the content
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
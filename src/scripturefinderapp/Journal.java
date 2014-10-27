/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

   /**
    * Written by Ashlie Horst
    * Collaborated with Ernesto Afane, Alex Mitchell, Paul O'Neil, and Sam H.
    */
public class Journal {
    
     private List<String> list;
     List<Entry> entryList;
     private List<String> bookList;
     private Map<String, String> topicMap;
     Entry entry;
     Propert prop;
    
    /*******************
     * JOURNAL CONSTRUCTOR
     */
    public Journal() {
        // Once big list of entries in the journal
        entryList = new ArrayList<>(); 
        bookList = new ArrayList<>();
        topicMap = new HashMap<>();
    }
    
   /*****************
     * OPEN TXT
     * @param fileName
     */
    public void openTxt(String fileName) {
        // This is reading in the txt file and making it a list
        List<String> list = readTxt(fileName);
        findEntry(list);
    }
    
     /*****************
     * RUN
     * Go through the terms and list of scriptures
     */
    public void run() throws IOException {
        readMapOfLists();  
        readBookList();
    }
    
    /*****************
     * ADD ENTRY
     * @param entry 
     */
    public void addEntry(Entry entry) {
       entryList.add(entry);
    }

    /****************
     * GET ENTRY
     * @return 
     */
    public Entry getEntry() {
        return entry;
    }
    
    /*************************
    * GET BOOK LIST
    ******************/
    public List<String> getBookList(){
        return bookList;
    }
    
    /********************
    * SEARCH ENTRY BOOK LIST
    * Passing in the list of books
     ************/
    public void searchEntryBookList(List<String> list) throws IOException{ 
        // Pass in the book "search" that the user inputted (string)
        // Nothing has been matched yet
        boolean isFound = false;
        
        // Go read in the appropriate file
        readBookList();
        
        // Go through the list of books
        for (String str : list) {
        boolean first = true;
            
        // Go through the list of entries and compare books
        for (Entry entry : entryList) {
                      
            for (int i = 0; i < entry.getScriptureList().size(); i++) {
                
                Scripture s = entry.getScriptureList().get(i);
                isFound = compareBooks(s.getBook(), str);
                
                // If it is the first to be found, print out the book name
                if (isFound && first) {
                    System.out.println(s.getBook());
                    first = false;
                }
                
                //  Then print out the date
                if (isFound) {
                    System.out.println("\t" + entry.getDate());
                    break;
                }                  
            }      
        }      
      }
    }
    
    /********************
    * READ BOOK LIST
    ***************/
    public void readBookList() throws IOException{
        // Open up the book.txt and put the books into a list
        prop = new Propert();
        String fileName = prop.getScriptureFile(); 
        String line;
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((line = reader.readLine()) != null){
                bookList.add(line);
            }
        } catch (IOException e){
            System.out.println("Error reading list from file");
        }
    }
    
    /****************
    * COMPARE BOOKS:  
    **********/
    public boolean compareBooks(String book, String mybook){
        boolean isEqual = false;
       
        if (book.equals(mybook)){
            isEqual = true;
        }      
        return isEqual;
    }
      
    /*************************
    * GET TOPIC LIST
    ******************/
    public Map<String, String> getTopicMap(){
        return topicMap;
    }
       
    /*************************
    * GET TOPIC LIST
    ******************/
    public List<Entry> getEntryList(){
        return entryList;
    }
    
    /******************
    * SEARCH ENTRY TOPIC LIST
    * Pass in the topic map
    *************/
    public void searchEntryTopicMap(Map<String, String> map){

        // Go through the topic list from terms.txt
        for (String term : map.keySet()) {
            term = term.toLowerCase();
            
            // Go through the entry objects
            for (Entry ent : entryList) {
                String con = ent.getContent();
                con = con.toLowerCase(); 
             
                if (con.contains(term)) {
                    if (!(ent.getTopicList().contains(map.get(term)))) {
                        ent.addTopic((map.get(term))); 
                    }                          
                }        
            } // end of entry for loop         
        } // end of map for loop
    }

      /*****************
     * READ MAP OF LISTS
     * Read the terms and put into a map
     */
    public Map<String, String> readMapOfLists() throws IOException {
        prop = new Propert();
        String file = prop.getTopicFile(); 
        
        Map<String, String> map = new HashMap<>();
        try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        
        while ((line = reader.readLine()) != null) {
           // Line looks like this:
           // faith:faith,beleive,beleif,faithful,faithfulness,trust

            String[] parts = line.split(":");
            String key = parts[0];
            // key has: faith
            
            String valueList = parts[1];
            // valueList now has "faith,beleive,beleif,faithful,faithfulness,trust"
            
            String[] terms = valueList.split(",");
 
            for (String term : terms) {
                map.put(term, key);
            }
        } // end of while
         reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }     

        searchEntryTopicMap(map);
        return map;
    }
    
      /*****************
     * READ TITLES
     * Reads the file into a list
     */
    public List<String> readTxt(String fileName) {
        List<String> list = new ArrayList<>();
   
        // When you read it in, you need to parse and then make a list of the scriptures
        // That list of scriptures will then be passed to the xml maker
        try {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        
        while ((line = reader.readLine()) != null) {
            list.add(line); 
        }

        } catch (IOException ex) {
            ex.printStackTrace();
            // IOExepction is a super class of File Not Found
        }       
        return list;           
    }
    
      /*****************
     * FIND ENTRY
     * Go through the list and make the entry objects
     */
    public void findEntry(List<String> list) {
       
        String date = "";
        Entry e = new Entry(); 
        Scripture scriptureObject;
        String content = "";
         
        for (int i = 0; i < list.size(); i++) {
            boolean newEntry = false;         
            if (list.get(i).equals("-----")) {
                // If it's not the new entry, add the content to it
                if (e != null) {
                    e.setContent(content);
                    content = "";    
                }        
                // Then you are free to make a new entry
                e = new Entry();
                e.setDate(list.get(i + 1)); 
                newEntry = true;      
            }
            
            // Set Content
            if (!(list.get(i).equals(e.getDate()) || list.get(i).equals("-----"))) {
                content += list.get(i);
            }
          
        Pattern script = Pattern.compile("\\w+\\s\\d[(0-9\\:\\-]*");
        Matcher found = script.matcher(list.get(i));
         
        String scripture = "";
        while (found.find()) {
            scriptureObject = new Scripture();
            scripture = found.group(); 
         
            // Split [book] [chapter]
            if (scripture.split(" ").length == 2) {
                String temp = "";
                String[] scrip = scripture.split(" ");
                // Set the book
                scriptureObject.setBook(scrip[0]);
                scriptureObject.setChapter(scrip[1]);
             
                // Split [chapter] [verse]
                if (scrip[1].split(":").length == 2) {
                    String[] parts = scrip[1].split(":");
                    scriptureObject.setChapter(parts[0]);
                    scriptureObject.setStartVerse(parts[1]);
                    
                    // Split [startVerse] [endVerse]
                    if (parts[1].split("-").length == 2) {
                        String[] verseLength = parts[1].split("-");
                        scriptureObject.setStartVerse(verseLength[0]);
                        scriptureObject.setEndVerse(verseLength[1]);
                    }               
                } 
                // Add scripture object to the scripture list
                e.addScripture(scriptureObject);  
            }  
            
        } // end of while loop
        
        // If a new entry was made, you are safe to add the object to the entry list
        if (newEntry) {   
            entryList.add(e);
        }         
      } // end of for loop
        
        // Set content of the last entry!
        e.setContent(content);
    }   
     
     /*****************
     * FIND ENTRY
     * Go through the list and make the entry objects
     * @param entry
     * @param content
     */
    public void makeSingleEntry(Entry entry, String content) { 
        Scripture scriptureObject;         
        Pattern script = Pattern.compile("\\w+\\s\\d[(0-9\\:\\-]*");
        Matcher found = script.matcher(content);
         
        String scripture = "";
        while (found.find()) {
            scriptureObject = new Scripture();
            scripture = found.group(); 
         
            // Split [book] [chapter]
            if (scripture.split(" ").length == 2) {
                String temp = "";
                String[] scrip = scripture.split(" ");
                // Set the book
                scriptureObject.setBook(scrip[0]);
                scriptureObject.setChapter(scrip[1]);
             
                // Split [chapter] [verse]
                if (scrip[1].split(":").length == 2) {
                    String[] parts = scrip[1].split(":");
                    scriptureObject.setChapter(parts[0]);
                    scriptureObject.setStartVerse(parts[1]);
                    
                    // Split [startVerse] [endVerse]
                    if (parts[1].split("-").length == 2) {
                        String[] verseLength = parts[1].split("-");
                        scriptureObject.setStartVerse(verseLength[0]);
                        scriptureObject.setEndVerse(verseLength[1]);
                    }               
                } 
            // Add scripture object to the scripture list
            entry.addScripture(scriptureObject); 
            }            
        } // end of while loop 
    }
    
      /*****************
     * BUILD XML DOCUMENT
     * Make tags of all the information in the entry objects
     * @param ent
     * @return 
     */
    public Document buildXmlDocument(List<Entry> ent) throws IOException {
        Document doc = null;
        try {
                // This was found at mkyong
                // http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
              
                // Make the book and topic list etc. before you make the xml
                run();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("journal");
		doc.appendChild(rootElement);
                
                // Entry elements
                for (Entry entry : ent) {  
                    
                    Element e = doc.createElement("entry");
                    rootElement.appendChild(e);
                    e.setAttribute("date", entry.getDate());

                    for (Scripture s : entry.getScriptureList()) {
                       Element scripture = doc.createElement("scripture");
                       e.appendChild(scripture);
                       scripture.setAttribute("book", s.getBook());
                       scripture.setAttribute("chapter", s.getChapter());
                       scripture.setAttribute("startverse", s.getStartVerse());          
                       scripture.setAttribute("endverse", s.getEndVerse());                 
                    }
                     
                    for (String t : entry.getTopicList()) {
                       Element topic = doc.createElement("topic");
                       e.appendChild(topic);
                       topic.appendChild(doc.createTextNode(t));
                    }
                       
                    // Content tag
                    Element content = doc.createElement("content");
                    e.appendChild(content);
                    content.appendChild(doc.createTextNode(entry.getContent()));
                } 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  }
        return doc;
    }
    
      /*****************
     * SAVE XML DOCUMENT
     * @param doc
     * @param file
     */
    public void saveDocument(Document doc, String file) {
 	try {
                // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
                
                // Make the XML look pretty
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                 
                // Create the new file
               // String newFile = "C:\\Users\\Ashlie\\Documents\\NetBeansProjects\\scripturefinderapp\\src\\scripturefinderapp\\inputFile.xml";
		DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(file));          
		transformer.transform(source, result);
 
		System.out.println("XML was saved!");   
    
	    }catch (TransformerException tfe) {
		tfe.printStackTrace();
            }
    }
      
      /*****************
     * SAVE TXT
     * Save the text file 
     */
    public void saveTxt(String fileName) throws FileNotFoundException {
        try {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        // Go through the entry list and write info for each
        for (Entry entry : entryList) {
            // Write in date and content to file
            writer.println("-----");
            writer.println(entry.getDate());
            writer.println(entry.getContent());
        }
        writer.close();
        } catch (UnsupportedEncodingException ex) {
           System.out.println("Boom in saveTxt!");
        }     
        System.out.println("TXT was saved!");   
    }
}





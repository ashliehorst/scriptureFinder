/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.w3c.dom.Document;

/**
 * FXML CONTROL CLASS
 * @author Ashlie
 */
public class FXMLControl implements Initializable {  
    @FXML
    private Label entriesLoaded;
    @FXML
    private Label scriptLoaded;
    @FXML
    private Label termsLoaded;
    @FXML
    private Label date;
    @FXML
    private Button newEntry;
    @FXML
    private Button search;
    @FXML
    private Button searchTopic;
    @FXML
    private Label journalContent;
    @FXML
    private Label list;
    @FXML
    private MenuItem newFile;
    @FXML
    private MenuItem openTxt;
    @FXML
    private MenuItem openXml;
    @FXML
    private MenuItem saveTxt;
    @FXML
    private MenuItem saveXml;
    @FXML
    private MenuItem quit;
    @FXML
    private MenuItem reset;
    @FXML
    private TextArea journalEntry;
    @FXML
    private TextArea entryBox;
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField searchTxt;
    @FXML
    private ListView listEntry;
    @FXML
    private ListView searchList;
    
    ObservableList items = FXCollections.observableArrayList();  
    ObservableList searchItems = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        
    }   
    
    private Stage stage;
    private Journal journal = new Journal(); 
    
    /********************
    * OPEN TXT
    * Open the text file
    * @param event
     ************/
    public void openTxt(ActionEvent event) {
        FileChooser chooser = new FileChooser();
            chooser.setTitle("Open TXT File");
            journal.run();	
            File open = chooser.showOpenDialog(stage); 
                try {
                    if (open != null) {
                        journal.openTxt(open.getPath()); 
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FXMLControl.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                // ERROR you need to clear the entry list first before you add more to it
                //   Make sure it is empty
                // TXT Display  
                String text = "";
                for (Entry entry : journal.getEntryList()) {
                    text += ("-----\n");
                    text += entry.getDate() + "\n";
                    text += entry.getContent() + "\n\n";
                }
                journalEntry.setText(text);
                tester();
    }
    
    /********************
    * TESTER
    * Test the threading
     ************/
    public void tester(){
        Threads tr = new Threads();
        int cEntry = 0;
        int cTopic = 0;
        int cScripture = 0;
        tr.myLabel = entriesLoaded;
        tr.myLabel2 = scriptLoaded;
        tr.myLabel3 = termsLoaded;
        tr.countEntries = cEntry;
        
        for (Entry entry : journal.getEntryList()){         
                cEntry++;
                tr.countEntries = cEntry;
               for (Scripture s : entry.getScriptureList()){
                   cScripture++;
                    tr.countScriptures = cScripture;
               }
               for (String topic : entry.getTopicList()){
                   cTopic++;
                   tr.countTopic = cTopic;
               }
        }
        Thread thread = new Thread(tr);
        thread.start();
   }
    
    /********************
    * SAVE XML
    * Save as an xml file
    * @param event
    ************/
    public void saveXml(ActionEvent event) {
        FileChooser chooser = new FileChooser();
            chooser.setTitle("Save XML File");

            File file = chooser.showSaveDialog(stage);           
            Document xml = null;
            xml = journal.buildXmlDocument(journal.getEntryList());
            if (file != null) {
                journal.saveDocument(xml, file.getPath());
            }
    }
    
    /********************
    * OPEN XML
    * Open xml file
    * @param event
    ************/
    public void openXml(ActionEvent event) {
        FileChooser chooser = new FileChooser();
            chooser.setTitle("Open XML File");
            journal.run();	
            File open = chooser.showOpenDialog(stage); 
                try {
                    if (open != null) {
                        new ReadFile().readFile(journal, open.getPath()); 
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FXMLControl.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                // XML Display 
                String text = "";
                for (Entry entry : journal.getEntryList()) {
                    items.add(entry);
                    text += ("-----\n");
                    text += entry.getDate() + "\n";
                    text += entry.getContent() + "\n\n";
                }
                journalEntry.setText(text);
               
                // Detail entry list 
                listEntry.setItems(items);
                listEntry.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>(){
            @Override
            public ListCell<Entry> call(ListView<Entry> listEntry) {
                ListCell<Entry> cell = new ListCell<Entry>() {
                    @Override
                    protected void updateItem(Entry ent, boolean e) {
                        if (ent != null) {
                            setText(ent.getDate());
                        }
                    }
                }; 
                return cell;
            }
        });  
        tester();
    } 
                        
    /********************
    * SAVE TXT
    * Save as a text file
    * @param event
     ************/
    public void saveTxt(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save TXT File");
 
        File file = chooser.showSaveDialog(stage);           
           
                try {
                   if (file != null) {
                    journal.saveTxt(file.getPath());
                   }
                } catch (IOException ex) {
                    System.out.println("Error with saveTxt button");
                }
    }
    
    /********************
    * SEARCH ENTRY TOPIC MAP
    * Search the topic map for matches
    * @param searchParam
     ************/
    public void searchEntryTopicMap(String searchParam){           
        // Go through the list of entries and compare books
        for (Entry entry : journal.entryList) {
            for (int i = 0; i < entry.getTopicList().size(); i++) {
                String s = entry.getTopicList().get(i);
                if (s.equals(searchParam)){
                    searchItems.add(entry);
                }                                       
            }      
        }        
    }
    
    /********************
    * SEARCH TOPIC
    * Get user input and search for the topic
    * @param event
     ************/
    public void searchTopic(ActionEvent event) {
        String searchTop = searchTxt.getText();                          
            searchEntryTopicMap(searchTop);
                // Detail entry list 
                searchList.setItems(searchItems);
                searchList.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>(){
            @Override
            public ListCell<Entry> call(ListView<Entry> listEntry) {
                ListCell<Entry> cell = new ListCell<Entry>() {
                    @Override
                    protected void updateItem(Entry ent, boolean e) {
                        if (ent != null) {
                            setText(ent.getDate());
                        }
                    }
                }; 
                return cell;
            }
        }); 
    }
    
    /********************
    * SEARCH ENTRY BOOK LIST
    * Passing in the list of books
    * @param searchParam
     ************/
    public void searchEntryBookList(String searchParam) { 
        // Pass in the book "search" that the user inputted (string)
        // Nothing has been matched yet
        boolean isFound = false;
            
        // Go through the list of entries and compare books
        for (Entry entry : journal.entryList) {
            for (int i = 0; i < entry.getScriptureList().size(); i++) {
                Scripture s = entry.getScriptureList().get(i);
                isFound = journal.compareBooks(s.getBook(), searchParam);
   
                //  Then print out the date
                if (isFound) {
                    searchItems.add(entry);
                }                       
            }      
        }
    }
    
    /********************
    * SEARCH 
    * Searches through the book list based on user input
    * @param event
     ************/
    public void search(ActionEvent event) {
        String searchBook = searchTxt.getText();                          
        searchEntryBookList(searchBook);

                // Detail entry list 
                //searchList.clear();
                searchList.setItems(searchItems);
                searchList.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>(){
            @Override
            public ListCell<Entry> call(ListView<Entry> listEntry) {
                ListCell<Entry> cell = new ListCell<Entry>() {
                    @Override
                    protected void updateItem(Entry ent, boolean e) {
                        if (ent != null) {
                            setText(ent.getDate());
                        }
                    }
                }; 
                return cell;
            }
            }); 
    }
    
    /********************
    * NEW ENTRY
    * Make a new entry object
    * @param event
     ************/
    public void newEntry(ActionEvent event) {
        Entry entry = new Entry();
            String entryDate = dateTxt.getText();
            String entryText = entryBox.getText();
            entry.setDate(entryDate);
            entry.setContent(entryText);
                        
            journal.makeSingleEntry(entry, entry.getContent());                     
                        
            dateTxt.clear();
            entryBox.clear();
            dateTxt.requestFocus();
                        
            if (entry.getContent() != null && !entry.getContent().equals("")) {
                journal.addEntry(entry);
            }
                        
            // New Entry Display 
            String text = "";
            for (Entry ent : journal.getEntryList()) {
                text += ("-----\n");
                text += ent.getDate() + "\n";
                text += ent.getContent() + "\n\n";
            }
            journalEntry.setText(text);
    }
    
    /********************
    * INIT
    * @param primaryStage
     ************/
    public void init (Stage primaryStage) {
        this.stage = primaryStage;
    }
}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package scripturefinderapp;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.application.Application;
//import static javafx.application.Application.launch;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import static javafx.geometry.HPos.RIGHT;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import javafx.stage.Window;
//import javafx.util.Callback;
//import org.w3c.dom.Document;
//
///**
// * Open a Journal from an XML file
// * Save a Journal to an XML file
// * Import a plain text file
// * Export a plain text file
// * Display the contents of the entries in the journal
// * Add a new entry to the journal
// * 
// * @author Ashlie
// */
//
//public class JavaFx extends Application {
// 
//   // private Journal journal;
//    private Stage stage;
//    TextArea txtContent;
//    private ListView list;
//    Label threadLabel;
//    Label dateLabel;
//    
//    @Override
//    public void start(Stage primaryStage) {  
//        
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scripturefinderapp/FXML.fxml"));
//            Parent root = loader.load();
//            
//            FXMLControl controller = (FXMLControl)loader.getController();
//            controller.init(primaryStage);
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        
//        
//        
//        Journal journal = new Journal();
//        stage = primaryStage;
// 
//        primaryStage.setTitle("Scripture Journal App");
//        GridPane grid = new GridPane();
//       // grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//        
//         // Thread Label
//        threadLabel = new Label("Thread tester");
//        grid.add(threadLabel, 5, 6, 1, 1);  
//                
//        // Open Button
//        Button open = new Button("Open XML File");
//        HBox hOpen = new HBox(10);
//        hOpen.setAlignment(Pos.BOTTOM_RIGHT);
//        hOpen.getChildren().add(open);
//        grid.add(hOpen, 0, 0);
//        
//        // Open Button
//        Button openTxt = new Button("Open TXT File");
//        HBox hOpenTxt = new HBox(10);
//        hOpenTxt.setAlignment(Pos.BOTTOM_RIGHT);
//        hOpenTxt.getChildren().add(openTxt);
//        grid.add(hOpenTxt, 1, 0);
//        
//         // Thread button
//        Button thread = new Button("thread");
//        HBox hthread = new HBox(10);
//        hthread.setAlignment(Pos.BOTTOM_RIGHT);
//        hthread.getChildren().add(thread);
//        grid.add(hthread, 5, 7);
//        
//         thread.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//                test();
//            }
//        }); 
//         
//        list = new ListView();
//        ObservableList items = FXCollections.observableArrayList();              
//        list.setPrefWidth(150);
//        list.setPrefHeight(200);        
//        grid.add(list, 3, 1, 1, 3);
//        
//
//        // Search Text field 
//        TextField searchBox = new TextField();
//        grid.add(searchBox, 5, 4, 1, 1);
//        
//        // Search Button
//        Button search = new Button("Search by Book");
//        HBox hsearch = new HBox(10);
//        hsearch.setAlignment(Pos.BOTTOM_RIGHT);
//        hsearch.getChildren().add(search);
//        grid.add(hsearch, 6, 4);
//        
//        search.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//            String searchBook = searchBox.getText();                    
//               // try {         
//                    //journal.searchEntryBookList(searchBook);
//                    journal.searchEntryTopicMap(searchBook);
//               // } catch (IOException ex) {
//               //     Logger.getLogger(JavaFx.class.getName()).log(Level.SEVERE, null, ex);
//               // }
//            searchBox.clear();
//            searchBox.requestFocus();
//            }
//        }); 
//                
//        openTxt.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//            FileChooser chooser = new FileChooser();
//            chooser.setTitle("Open TXT File");
// 			
//            File open = chooser.showOpenDialog(stage); 
//                try {
//                    if (open != null) {
//                        journal.openTxt(open.getPath()); 
//                    }
//                } catch (Exception ex) {
//                    Logger.getLogger(JavaFx.class.getName()).log(Level.SEVERE, null, ex);
//                }
//               
//                // ERROR you need to clear the entry list first before you add more to it
//                //   Make sure it is empty
//                // TXT Display  
//                txtContent = new TextArea();
//                txtContent.setPrefColumnCount(40);
//                String text = "";
//                for (Entry entry : journal.getEntryList()) {
//                    text += ("-----\n");
//                    text += entry.getDate() + "\n";
//                    text += entry.getContent() + "\n\n";
//                }
//                txtContent.setText(text);
//           
//                txtContent.setPrefColumnCount(40);
//                txtContent.setPrefRowCount(80); 
//                grid.add(txtContent, 0, 4, 3, 2);
//                // end of display
//            }
//           }); 
//               
//        open.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//            FileChooser chooser = new FileChooser();
//            chooser.setTitle("Open XML File");
//            			
//            File open = chooser.showOpenDialog(stage); 
//                try {
//                    if (open != null) {
//                        new ReadFile().readFile(journal, open.getPath()); 
//                    }
//                } catch (Exception ex) {
//                    Logger.getLogger(JavaFx.class.getName()).log(Level.SEVERE, null, ex);
//                }
//               
//                // XML Display 
//                // ERROR you need to clear the entrylist before you fill it
//                txtContent = new TextArea();
//                txtContent.setPrefColumnCount(40);
//                String text = "";
//                for (Entry entry : journal.getEntryList()) {
//                    items.add(entry);
//                    text += ("-----\n");
//                    text += entry.getDate() + "\n";
//                    text += entry.getContent() + "\n\n";
//                }
//                // Detail entry list 
//                list.setItems(items);
//                list.setCellFactory(new Callback<ListView<Entry>, ListCell<Entry>>(){
//            @Override
//            public ListCell<Entry> call(ListView<Entry> listEntry) {
//                ListCell<Entry> cell = new ListCell<Entry>() {
//                    @Override
//                    protected void updateItem(Entry ent, boolean e) {
//                        if (ent != null) {
//                            setText(ent.getDate());
//                        }
//                    }
//                }; 
//                return cell;
//            }
//        });
//                txtContent.setText(text);           
//                txtContent.setPrefColumnCount(40);
//                txtContent.setPrefRowCount(80); 
//                grid.add(txtContent, 0, 4, 3, 2);
//                // end of display               
//            } // end of open handle
//            });
//                // New Entry Button
//                Button entry = new Button("Create New Entry");
//                HBox hEntry = new HBox(10);
//                hEntry.setAlignment(Pos.BOTTOM_RIGHT);
//                hEntry.getChildren().add(entry);
//                grid.add(hEntry, 0, 2);
//                
//                 // Save XML button
//                Button saveXml = new Button("Save as XML File");
//                HBox hSaveXml = new HBox(10);
//                hSaveXml.setAlignment(Pos.BOTTOM_RIGHT);
//                hSaveXml.getChildren().add(saveXml);
//                grid.add(hSaveXml, 0, 1);
//                
//                // Save TXT button
//                Button saveTxt = new Button("Save as TXT File");
//                HBox hSaveTxt = new HBox(10);
//                hSaveTxt.setAlignment(Pos.BOTTOM_RIGHT);
//                hSaveTxt.getChildren().add(saveTxt);
//                grid.add(hSaveTxt, 1, 1);
//                
//            saveTxt.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//            FileChooser chooser = new FileChooser();
//            chooser.setTitle("Save TXT File");
// 
//            File file = chooser.showSaveDialog(stage);           
//           
//               try {
//                   if (file != null) {
//                    journal.saveTxt(file.getPath());
//                   }
//                } catch (IOException ex) {
//                    System.out.println("Error with saveTxt button");
//                }
//            }
//        });
//         
//          saveXml.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//            FileChooser chooser = new FileChooser();
//            chooser.setTitle("Save XML File");
// 
//            File file = chooser.showSaveDialog(stage);           
//            Document xml = null;
//                try {
//                    xml = journal.buildXmlDocument(journal.getEntryList());
//                } catch (IOException ex) {
//                    Logger.getLogger(JavaFx.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            if (file != null) {
//                journal.saveDocument(xml, file.getPath());
//            }
//            }
//        });
//          
//        entry.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//
//                // Date
//                dateLabel = new Label("Date (yyyy-mm-dd)");
//                grid.add(dateLabel, 5, 0, 1, 1);
//                
//                // Date Text field 
//                TextField dateBox = new TextField();
//                grid.add(dateBox, 5, 1, 1, 1);
//
//                // Label for Add New Entry
//                Label entryLabel = new Label("Entry Content");
//                grid.add(entryLabel, 5, 2, 1, 1);
//                
//                // Entry txt box
//                TextArea entryBox = new TextArea();
//                grid.add(entryBox, 5, 4, 1, 1);
//        
//                entryBox.setPrefWidth(350);
//                entryBox.setPrefHeight(200); 
//                
//                // Create button
//                Button create = new Button("Add Entry");
//                HBox hCreate = new HBox(10);
//                hCreate.setAlignment(Pos.BOTTOM_RIGHT);
//                hCreate.getChildren().add(create);
//                grid.add(hCreate, 5, 5);
//        
//                create.setOnAction(new EventHandler<ActionEvent>() {
//
//                    @Override
//                    public void handle(ActionEvent e) {
//                        Entry entry = new Entry();
//                        String entryDate = dateBox.getText();
//                        String entryText = entryBox.getText();
//                        entry.setDate(entryDate);
//                        entry.setContent(entryText);
//                        
//                        journal.makeSingleEntry(entry, entry.getContent());                     
//                        
//                        dateBox.clear();
//                        entryBox.clear();
//                        dateBox.requestFocus();
//                        
//                        if (entry.getContent() != null && !entry.getContent().equals("")) {
//                            journal.addEntry(entry);
//                        }
//                        
//                        // New Entry Display 
//                        txtContent = new TextArea();
//                        txtContent.setPrefColumnCount(40);
//                        String text = "";
//                        for (Entry ent : journal.getEntryList()) {
//                            text += ("-----\n");
//                            text += ent.getDate() + "\n";
//                            text += ent.getContent() + "\n\n";
//                        }
//                        txtContent.setText(text);
//           
//                        txtContent.setPrefColumnCount(40);
//                        txtContent.setPrefRowCount(80); 
//                        grid.add(txtContent, 0, 4, 3, 2);
//                        // end of display 
//                    }
//                });
//            }
//        });
//             
//        Scene scene = new Scene(grid, 775, 575);
//        primaryStage.setScene(scene);
//        primaryStage.show();  
//    }
//    
//    public void test() {
//        Threads thread = new Threads();
//        thread.myLabel = threadLabel;
//        
//        Thread newThread = new Thread(thread);
//        newThread.start();
//    }
//
//    public static void main(String[] args) {       
//        launch(args);     
//    }
//}
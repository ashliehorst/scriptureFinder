/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ashlie
 */
public class FXMLControl implements Initializable {
    private Stage stage;
    
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
    private TextArea entryTxt;
    @FXML
    private TextField dateTxt;
    
    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        
    }
    
    public void init (Stage primaryStage) {
        this.stage = primaryStage;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author Ashlie
 */
public class FXMLControl implements Initializable {
    private Stage stage;
    
    @Override
    public void initialize(URL location, ResourceBundle bundle) {
        
    }
    
    public void init (Stage primaryStage) {
        this.stage = primaryStage;
    }
}

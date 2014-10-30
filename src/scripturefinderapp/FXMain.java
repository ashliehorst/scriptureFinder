/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Collaborators include Ernesto Afane, Paul O'Neil, and Sam Hibbard    
 * @author Ashlie
 */
public class FXMain extends Application {
    
    /********************
    * START
    * Get everything ready!
     ************/
    @Override
    public void start(Stage primaryStage) {  
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scripturefinderapp/FXML.fxml"));
            Parent root = loader.load();
            
            FXMLControl controller = (FXMLControl)loader.getController();
            controller.init(primaryStage);
            
            Scene scene = new Scene(root, 900, 650);  
            
            primaryStage.setTitle("Scripture Finder App");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    

    /**
     * MAIN
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

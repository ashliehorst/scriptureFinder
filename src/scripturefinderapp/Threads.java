/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripturefinderapp;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Ashlie
 */
public class Threads implements Runnable {
    public Label myLabel;
    
    @Override
    public void run() {
        try {
            Thread.sleep(1000); // wait one second
            test();
            } catch (Exception ex) {
            ex.printStackTrace();
            }
    }
    
    public void test() {
        Platform.runLater(new Runnable() {
        
        @Override
        public void run() {
            myLabel.setText("In the setText");
        }
        });
    }
}

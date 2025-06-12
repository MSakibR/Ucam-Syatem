package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.*;


public class AddTopic {

    @FXML
    private TextField courseNameTextField;

    @FXML
    private TextField topicNameTextField;
    
    @FXML
    private Label idLabel;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    private void handleAddButtonAction(ActionEvent event) throws IOException {
        String courseTitle = courseNameTextField.getText();
        String topic = topicNameTextField.getText();

            ArrayList<String> topics = new ArrayList<>();
            topics.add(topic); 
            
            String courseId = Main.cms.addCourse(courseTitle, topics);
            idLabel.setText("ID: " + courseId); 
            
            DataHandler.saveData(Main.cms);
            JOptionPane.showMessageDialog(null, "Topic added to course successfully.");
    }
    
    @FXML
    private void handleEraseButtonAction(ActionEvent event) {
    	courseNameTextField.setText("");
        topicNameTextField.setText("");
        idLabel.setText("");
        
    }
    
    
}


package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.*;
import uap.InvalidCourseException;
import uap.InvalidUserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class OfferCourseController {

    @FXML
    private TextField courseIdTextField;

    @FXML
    private TextField instructorIdTextField;

    @FXML
    private TextField startDatePicker;

    @FXML
    private TextField durationTextField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleOfferCourseButtonAction() throws FileNotFoundException, IOException {
        String courseId = courseIdTextField.getText();
        String instructorId = instructorIdTextField.getText();
        String startDate = startDatePicker.getText();
        int duration = Integer.parseInt(durationTextField.getText());

        try {
            Main.cms.offerCourse(courseId, instructorId, startDate, duration);
            statusLabel.setText("Course offered successfully.");
            DataHandler.saveData(Main.cms);
        } catch (InvalidCourseException | InvalidUserException e) {
            statusLabel.setText("Error: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleBackButtonAction() throws IOException {
	AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
	Scene scene = new Scene(root);			
	Main.stage.setScene(scene);
	Main.stage.show();
   
}
    
    @FXML
    private void handleEraseButtonAction(ActionEvent event) {
    	courseIdTextField.setText("");
    	instructorIdTextField.setText("");
    	startDatePicker.setText("");
    	durationTextField.setText("");
    	statusLabel.setText("");
    	
        
    }
    
}

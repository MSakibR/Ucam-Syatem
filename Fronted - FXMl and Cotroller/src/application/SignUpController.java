package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;
import javax.swing.JOptionPane;
import uap.*;

public class SignUpController {
	
	@FXML private TextField nameTextField;
	@FXML private TextField ageTextField;
	
	@FXML
	private void handleSignupButtonAction(ActionEvent event) throws IOException {
	    String name = nameTextField.getText().trim();
	    String ageStr = ageTextField.getText().trim();

	   
	    if (name.isEmpty() || ageStr.isEmpty()) {
	        showErrorAlert("Please enter name and age.");
	        return;
	    }

	    try {
	    	 int age = Integer.parseInt(ageStr);       
	            String loginId = Main.cms.addStudent(name, age); 
	            DataHandler.saveData(Main.cms);        
	            JOptionPane.showMessageDialog(null, "Student information saved successfully.\nGenerated ID: " + loginId);
	        } catch (NumberFormatException e) {
	        showErrorAlert("Age must be a number.");
	    }
	}

	private void showErrorAlert(String message) {
	    JOptionPane.showMessageDialog(null, message);
	}

	      @FXML
	      private void handleLoginButtonAction() throws IOException {
	
	    	  AnchorPane root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
				Scene scene = new Scene(root);			
				Main.stage.setScene(scene);
				Main.stage.show();

	}    
}




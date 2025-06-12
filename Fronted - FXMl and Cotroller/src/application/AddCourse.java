package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.DataHandler;
import uap.InvalidCourseException;
import uap.InvalidUserException;

public class AddCourse {
	
	    @FXML
	    private TextField nameTextField;
	 
	    @FXML
	    private Label idLabel;

	    @FXML
	    private void handleConfirmButtonAction() throws InvalidCourseException, InvalidUserException, FileNotFoundException, IOException {
	       
	    	String name = nameTextField.getText().trim();
			String courseId = Main.cms.addCourse(name);
		    
		   idLabel.setText("Generated Admin ID: " + courseId); 
	       DataHandler.saveData(Main.cms);
	       JOptionPane.showMessageDialog(null, "Course Added");
	       
            nameTextField.setText("");

	    }
         
	    @FXML
        private void handleBackButtonAction() throws IOException {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
		Scene scene = new Scene(root);			
		Main.stage.setScene(scene);
		Main.stage.show();
       
    }
    
}

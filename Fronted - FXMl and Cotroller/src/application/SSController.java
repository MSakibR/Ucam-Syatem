package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.Course;
import uap.InvalidCourseException;

public class SSController {
	 @FXML
	    private TextField searchTextField;

	    @FXML
	    private ListView<String> searchResultsListView;

	    @FXML
	    private void handleBackButtonAction() throws IOException {
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	    }
	    
	    @FXML
	    private void handleCourseButtonAction() throws IOException {
	    	String search = searchTextField.getText().trim();
	        try {
	            Course course = Main.cms.findCourse(search);
	           
	            searchResultsListView.getItems().clear();
	            searchResultsListView.getItems().add(course.getId() + " - " + course.getTitle());
	        } catch (InvalidCourseException e) {
	          
	            searchResultsListView.getItems().clear();
	            searchResultsListView.getItems().add("Course not found.");
	        }
	    }

	    
}

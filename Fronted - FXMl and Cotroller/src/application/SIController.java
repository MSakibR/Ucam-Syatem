package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.Course;
import uap.InvalidCourseException;
import uap.InvalidUserException;
import uap.User;

import java.io.IOException;

public class SIController {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> searchResultsListView;

    @FXML
    private void handleBackButtonAction() throws IOException {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("InstructorHomepage.fxml"));
		Scene scene = new Scene(root);			
		Main.stage.setScene(scene);
		Main.stage.show();
    }
    
    @FXML
    private void handleCourseButtonAction() throws IOException {
    	String courseId = searchTextField.getText();
        try {
            Course course = Main.cms.findCourse(courseId);
           
            searchResultsListView.getItems().clear();
            searchResultsListView.getItems().add(course.getId() + " - " + course.getTitle());
        } catch (InvalidCourseException e) {
          
            searchResultsListView.getItems().clear();
            searchResultsListView.getItems().add("Course not found.");
        }
    }

    
    @FXML
    private void handleStudentButtonAction() throws IOException {
    	String studentId = searchTextField.getText();
        try {
            User student = Main.cms.findUser(studentId);
           
            searchResultsListView.getItems().clear();
            searchResultsListView.getItems().add(student.toString());
        } catch (InvalidUserException e) {
          
            searchResultsListView.getItems().clear();
            searchResultsListView.getItems().add("Student not found.");
        }
    }
    
}

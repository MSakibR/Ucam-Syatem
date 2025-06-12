package application;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import uap.*;

public class SearchController {

    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> searchResultsListView;

    @FXML
    private void handleBackButtonAction() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
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
    private void handleInstructorButtonAction() throws IOException, InvalidUserException {
        String instructorId = searchTextField.getText();
        try {
           
        	User instructor = Main.cms.findUser(instructorId);
            
            searchResultsListView.getItems().clear();
            searchResultsListView.getItems().add(instructor.toString());
        } catch (Exception e) {
           
            searchResultsListView.getItems().clear();
            searchResultsListView.getItems().add("Instructor not found.");
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

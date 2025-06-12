package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.*;
import uap.InvalidCourseException;
import uap.InvalidUserException;
import uap.User;

public class ConductController implements Initializable{
	@FXML
    private TextField courseIdTextField;
    @FXML
    private TextField stID;
    @FXML
    private TextField CRid;

    @FXML
    private ListView<String> courseListView;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        courseListView.getItems().clear();
        ArrayList<String> courseOfferInfo = new ArrayList<>();
        
        for (CourseRecord record : Main.cms.getOfferedCourses()) {
            try {
                Course course = Main.cms.findCourse(record.getCourseId());
                User user = Main.cms.findUser(record.getInstructorId());

                
                String courseTid = course.getId();
                String courseTitle = course.getTitle();
                String instructorName = user.getName();
                

                String courseOfferDetails = String.format("Course ID: %s | Course Title: %s | Instructor: %s ", 
                		courseTid, courseTitle, instructorName);
                
                courseOfferInfo.add(courseOfferDetails);
            } catch (InvalidCourseException | InvalidUserException e) {
                    
            	courseListView.getItems().clear();
            	courseListView.getItems().add("Course not found.");
            }
        }
        
        courseListView.getItems().addAll(courseOfferInfo);
    }


    
    @FXML
    private void handleSearchButtonAction() {
    	    String searchText = courseIdTextField.getText().toLowerCase(); // Convert search text to lowercase for case-insensitive search
    	   ArrayList<String> filteredCourses = new ArrayList<>();
		for (String courseInfo : courseListView.getItems()) {
		    if (courseInfo.toLowerCase().contains(searchText)) {
		        filteredCourses.add(courseInfo);
		    }
		}
		courseListView.getItems().setAll(filteredCourses);
    }
    	

   

    @FXML
    private void handleBackButtonAction() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("InstructorHomepage.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}

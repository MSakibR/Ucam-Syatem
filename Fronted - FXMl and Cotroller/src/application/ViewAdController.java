package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import uap.*;

public class ViewAdController {

	    @FXML
	    private ListView<String> listView;
	    

	    @FXML
	    private void handleBackButtonAction(ActionEvent event) throws IOException {
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	    }
	    
	    @FXML
	    public void OfferCourseButton() throws IOException{
	        listView.getItems().clear();
	        ArrayList<String> courseOfferInfo = new ArrayList<>();
	        
	        for (CourseRecord record : Main.cms.getOfferedCourses()) {
	            try {
	                Course course = Main.cms.findCourse(record.getCourseId());
	                User user = Main.cms.findUser(record.getInstructorId());

	                String courseTitle = course.getTitle();
	                String instructorName = user.getName();
	                String startDate = record.getStart_date().toString();
	                int duration = record.getDuration();
	                ArrayList<String> topics = course.getTopics();

	                String topicsString = String.join("|", topics);

	                String courseOfferDetails = String.format("%s - %s | Start Date: %s | Duration: %d hours | Topics: %s", courseTitle, instructorName, startDate, duration, topicsString);
	                
	                //String courseOfferDetails = String.format("%s - %s | Start Date: %s | Duration: %d hours", courseTitle, instructorName, startDate, duration);
	                courseOfferInfo.add(courseOfferDetails);
	            } catch (InvalidCourseException | InvalidUserException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        listView.getItems().addAll(courseOfferInfo);
	    }

	    @FXML
	    private void handleCourseListButtonAction(ActionEvent event) {
	        
	        ObservableList<String> courseList = FXCollections.observableArrayList();
	        for (Course course : Main.cms.getCourses()) {
	            courseList.add(course.getId() + " - " + course.getTitle());
	        }
	        listView.setItems(courseList);
	    }

	    @FXML
	    private void handleTeacherListButtonAction(ActionEvent event) {
	       
	        ObservableList<String> instructorList = FXCollections.observableArrayList();
	        for (User user : Main.cms.getUsers()) {
	            if (user instanceof Instructor) {
	                instructorList.add(user.getId() + " - " + user.getName());
	            }
	        }
	        listView.setItems(instructorList);
	    }


	    @FXML
	    private void handleStudentListButtonAction(ActionEvent event) {
	      
	        ObservableList<String> studentList = FXCollections.observableArrayList();
	        for (User user : Main.cms.getUsers()) {
	            if (user instanceof Student) {
	                studentList.add(user.getId() + " - " + user.getName());
	            }
	        }
	        listView.setItems(studentList);
	    }
	    
	    

}



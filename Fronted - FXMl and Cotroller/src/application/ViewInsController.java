package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import uap.Course;
import uap.CourseRecord;
import uap.InvalidCourseException;
import uap.InvalidUserException;
import uap.Student;
import uap.User;

public class ViewInsController {
	

	    @FXML
	    private ListView<String> listView;

	    @FXML
	    private void initialize() {
	       
	    }

	    @FXML
	    private void handleBackButtonAction(ActionEvent event) throws IOException {
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("InstructorHomepage.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
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
	    private void handleCourseButtonAction(ActionEvent event) {
	    	ObservableList<String> courseList = FXCollections.observableArrayList();
	        for (Course course : Main.cms.getCourses()) {
	            courseList.add(course.getId() + " - " + course.getTitle());
	        }
	        listView.setItems(courseList);  
	    }
	    
	}


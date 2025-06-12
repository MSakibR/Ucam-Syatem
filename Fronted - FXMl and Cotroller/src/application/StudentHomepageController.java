package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.*;

public class StudentHomepageController implements Initializable{
	
	
    @FXML
    private TextField CRid;
	    @FXML
	    private ListView<String> ListViews;
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	        ListViews.getItems().clear();
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
	        
	        ListViews.getItems().addAll(courseOfferInfo);
	    }



	 
	 @FXML
	 private void handleBackButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleSearchButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("SearchStu.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleViewButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("view Stu.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleEnrollandCanButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("EnrollAndCancel.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }

	 @FXML
	    private void handleAttendClassButtonAction() throws IOException {
	      //  String studentId = stID.getText().trim();
	        String courseRecordId = CRid.getText().trim();

	        try {
	            Student student = (Student) Main.currentUser;
	            Main.cms.attendClass(student, courseRecordId);
	            DataHandler.saveData(Main.cms);
	            JOptionPane.showMessageDialog(null, "Student marked as attending the class.");
	          //  stID.setText("");
	            CRid.setText("");
	        } catch (InvalidCourseException  e) {
	            JOptionPane.showMessageDialog(null, "Error: Not found");
	        }
	    }

	    @FXML
	    private void handleCompleteCourseButtonAction() throws IOException {
	        //String studentId = stID.getText().trim();
	        String courseRecordId = CRid.getText().trim();

	        try {
	            Student student = (Student) Main.currentUser;
	            Main.cms.completeCourse(student, courseRecordId);
	            DataHandler.saveData(Main.cms);
	            JOptionPane.showMessageDialog(null, "Student marked as completed the course.");
	            //stID.setText("");
	            CRid.setText("");
	        } catch (InvalidCourseException e) {
	            JOptionPane.showMessageDialog(null, "Error: Not found");
	        }
	    }
	 
}

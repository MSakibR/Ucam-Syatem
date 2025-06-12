package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.Course;
import uap.CourseRecord;
import uap.DataHandler;
import uap.InvalidCourseException;
import uap.InvalidUserException;
import uap.Student;
import uap.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class EnrollAndCancelController implements Initializable{

    @FXML
    private TextField courseIdTextField;
    
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

                
                String courseId = record.getId(); 
                String courseTitle = course.getTitle();
                String instructorName = user.getName();
                String startDate = record.getStart_date().toString();
                int duration = record.getDuration();

                String courseOfferDetails = String.format("Offer Course ID: %s | Course Title: %s | Instructor: %s | Start Date: %s | Duration: %d hours", 
                                                          courseId, courseTitle, instructorName, startDate, duration);
                
                courseOfferInfo.add(courseOfferDetails);
            } catch (InvalidCourseException | InvalidUserException e) {
                e.printStackTrace();
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
    private void handleEnrollButtonAction() throws FileNotFoundException, IOException {
        //String studentId = stID.getText().trim();
        String courseRecordId = CRid.getText().trim();

        try {
            Student student = (Student) Main.currentUser; 
            Main.cms.registerStudent(student, courseRecordId); 
            DataHandler.saveData(Main.cms); 
            JOptionPane.showMessageDialog(null, "Student enrolled successfully.");
            //stID.setText("");
            CRid.setText("");
        } catch (InvalidCourseException  e) {
            JOptionPane.showMessageDialog(null, "Error: Not found");
        }
    }



    @FXML
    private void handleBackButtonAction() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }

}

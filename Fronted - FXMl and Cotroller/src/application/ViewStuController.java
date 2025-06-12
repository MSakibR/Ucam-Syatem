package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import uap.*;

public class ViewStuController {


    @FXML
    private ListView<String> listView;

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    @FXML
    private void handleCompleteCourseListButtonAction(ActionEvent event) {
        ObservableList<String> completedCoursesList = FXCollections.observableArrayList();
        //String studentId = stID.getText().trim();

        try {
            Student student = (Student) Main.currentUser;
            if (student != null) {
                for (String courseId : student.getCourseRecordIds().keySet()) {
                    if (student.getCourseRecordIds().get(courseId).equals("completed")) {
                        CourseRecord record = Main.cms.findCourseRecord(courseId);
                        Course course = Main.cms.findCourse(record.getCourseId());
                        completedCoursesList.add(course.getTitle() + " - " + record.getStart_date().toString());
                    }
                }
            }
        } catch ( InvalidCourseException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }

        listView.getItems().clear();
        listView.setItems(completedCoursesList);
    }

    @FXML
    private void handleEnrollCourseListButtonAction(ActionEvent event) throws NotAvailableException, InvalidUserException {
        ObservableList<String> enrolledCoursesList = FXCollections.observableArrayList();
       // String studentId = stID.getText().trim();

        try {
            Student student = (Student) Main.currentUser;
            if (student != null) {
                for (String courseId : student.getCourseRecordIds().keySet()) {
                    if (student.getCourseRecordIds().get(courseId).equals("registered")) {
                        CourseRecord record = Main.cms.findCourseRecord(courseId);
                        Course course = Main.cms.findCourse(record.getCourseId());
                        enrolledCoursesList.add(course.getTitle() + " - " + record.getStart_date().toString());
                    }
                }
            }
        } catch (InvalidCourseException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }

        listView.getItems().clear();
        listView.setItems(enrolledCoursesList);
    }

    @FXML
    private void handleAttendClassListButtonAction(ActionEvent event) {
        ObservableList<String> attendingClassesList = FXCollections.observableArrayList();
        //String studentId = stID.getText().trim();

        try {
            Student student = (Student) Main.currentUser;
            if (student != null) {
                for (String courseId : student.getCourseRecordIds().keySet()) {
                    if (student.getCourseRecordIds().get(courseId).equals("in-progress")) {
                        CourseRecord record = Main.cms.findCourseRecord(courseId);
                        Course course = Main.cms.findCourse(record.getCourseId());
                        attendingClassesList.add(course.getTitle() + " - " + record.getStart_date().toString());
                    }
                }
            }
        } catch (InvalidCourseException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            e.printStackTrace();
        }

        listView.getItems().clear();
        listView.setItems(attendingClassesList);
    }
}

package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uap.DataHandler;

public class AddOthers {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Label uniqueIdLabel;

    @FXML
    private void initialize() {
        
    }

    @FXML
    private void handleAdminButtonAction(ActionEvent event) throws IOException {
        String adminName = idTextField.getText().trim();
        int adminAge = Integer.parseInt(ageTextField.getText().trim());
        String adminId = Main.cms.addAdmin(adminName, adminAge);
        uniqueIdLabel.setText("Generated Admin ID: " + adminId);
        DataHandler.saveData(Main.cms);
        idTextField.setText("");
        ageTextField.setText("");
    }

    @FXML
    private void handleInstructorButtonAction(ActionEvent event) throws IOException {
        String instructorName = idTextField.getText().trim();
        int instructorAge = Integer.parseInt(ageTextField.getText().trim());
        String instructorId = Main.cms.addInstructor(instructorName, instructorAge);
       
        uniqueIdLabel.setText("Generated Instructor ID: " + instructorId);
        DataHandler.saveData(Main.cms);
        idTextField.setText("");
        ageTextField.setText("");
    }

    @FXML
    private void handleStudentButtonAction(ActionEvent event) throws IOException {
        String studentName = idTextField.getText().trim();
        int studentAge = Integer.parseInt(ageTextField.getText().trim());
        
       
        String studentId = Main.cms.addStudent(studentName, studentAge);
        
        uniqueIdLabel.setText("Generated Student ID: " + studentId);
        DataHandler.saveData(Main.cms);
        idTextField.setText("");
        ageTextField.setText("");
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}

package application;

import java.io.IOException;
import uap.*;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private TextField idTextField;

    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws IOException {
        String userId = idTextField.getText().trim();
        
        try {
            User user = Main.cms.findUser(userId);
            if (user != null) {
            	 Main.currentUser = user;
            	 
                if (user instanceof Admin) {
                    
                    AnchorPane root = FXMLLoader.load(getClass().getResource("AdminHomepage.fxml"));
                    Scene scene = new Scene(root);            
                    Main.stage.setScene(scene);
                    Main.stage.show();
                    
                } else if (user instanceof Instructor) {
                    
                    AnchorPane root = FXMLLoader.load(getClass().getResource("InstructorHomepage.fxml"));
                    Scene scene = new Scene(root);            
                    Main.stage.setScene(scene);
                    Main.stage.show();
                    
                } else if (user instanceof Student) {
                   
                    AnchorPane root = FXMLLoader.load(getClass().getResource("StudentHomepage.fxml"));
                    Scene scene = new Scene(root);            
                    Main.stage.setScene(scene);
                    Main.stage.show();
                }
            } else {
                
            	JOptionPane.showMessageDialog(null, "Not Found");
            }
        } catch (InvalidUserException e) {
           
        	JOptionPane.showMessageDialog(null, "Not Found");
        }
    }

    

    @FXML
    private void handleSignupButtonAction(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("StudentSignup.fxml"));
        Scene scene = new Scene(root);            
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
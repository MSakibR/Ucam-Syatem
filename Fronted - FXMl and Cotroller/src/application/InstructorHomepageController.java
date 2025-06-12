package application;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class InstructorHomepageController {

	 @FXML
	 private void handleBackButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleSearchButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("SearchIns.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleViewButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("view Ins.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleConductButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("Conduct.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
}

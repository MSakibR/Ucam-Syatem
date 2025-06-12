package application;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class AdminHomePageControler {

	    @FXML
	    private void handleAddCourseButtonAction() throws IOException {
		 AnchorPane root = FXMLLoader.load(getClass().getResource("AddCourse.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
		 
	         }
	 
	    @FXML
	    private void handleSetOffereButtonAction() throws IOException {
		 AnchorPane root = FXMLLoader.load(getClass().getResource("OfferCourse.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
		 
	         }
	    
	 @FXML
	 private void handleBackButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleAddTopicButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("AddTopic.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
	 
	 @FXML
	 private void handleAddOthersButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("AddOthers.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }

     @FXML
	 private void handleSearchButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("SearchAd.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
     
     @FXML
	 private void handleViewButtonAction() throws IOException {
	    	
	    	AnchorPane root = FXMLLoader.load(getClass().getResource("ViewAll.fxml"));
			Scene scene = new Scene(root);			
			Main.stage.setScene(scene);
			Main.stage.show();
	        
	    }
     
     
}

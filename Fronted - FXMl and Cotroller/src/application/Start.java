package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Start {

	@FXML
	private void handleStartAction() throws IOException {
    	
		AnchorPane root = FXMLLoader.load(getClass().getResource("StudentLogin.fxml"));
		Scene scene = new Scene(root);			
		Main.stage.setScene(scene);
		Main.stage.show();
    
}
	
}

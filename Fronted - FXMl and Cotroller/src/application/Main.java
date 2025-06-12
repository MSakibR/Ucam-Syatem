package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uap.*;
import uap.DataHandler;

public class Main extends Application {
    
	public static CourseManagementSystem cms = null;
    public static Stage stage;
    public static User currentUser;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("AllUser.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Course Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
    	try {
			cms = DataHandler.loadData();
			System.out.println("Load");
			 //System.out.println("ID: " + cms.getUsers().get(2).getId());
			 //System.out.println("ID: " + cms.getCourses().get(0).getId());
			//System.out.println("ID: " + cms.getOfferedCourses().get(0).getId());
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("New Data");
			cms = new CourseManagementSystem("UAP");
			cms.addAdmin("Sakib", 22);
			try {
				DataHandler.saveData(cms);
			}catch(IOException e1) {
			e1.printStackTrace();
			}
		}
        launch(args);
    }
}
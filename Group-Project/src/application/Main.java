package application;

import java.io.FileInputStream;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Authors Natalie Carlos Romeo 
 * The Main class serves as the start of the JavaFX application. It contains the main method which
 * launches the application and the start method which is called after the application has been launched.
 */

public class Main extends Application {
	private static Stage stage;
	@Override
	public void start(Stage primaryStage) {

		/**
		 * The start method is called after the application has been launched. It creates the main view of
		 * the application and sets it as the scene to be displayed on the primary stage.
		 * @param primaryStage The primary stage of the application.
		 * Exception If an error occurs during the loading of the InputView.fxml file.
		 */

		try {
			stage=primaryStage;	
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/InputView.fxml"));
			RoutePlannerController controller = (RoutePlannerController)loader.getController();
			controller.testStage = primaryStage;		
			Scene scene = new Scene(root,600,700);
			primaryStage.setScene(scene);
			primaryStage.setTitle("RouteFinder");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * The getStage method returns the primary stage of the application.
	 * @return The primary stage of the application.
	 */
	public static Stage getStage() {		
		return stage;
	}

	/**
	 * The main method is the entry point for the JavaFX application. It launches the application.
	 * @param args The command line arguments passed to the application.
	 */

	public static void main(String[] args) {
		launch(args);
	}
}
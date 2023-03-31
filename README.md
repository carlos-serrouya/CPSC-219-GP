# CPSC-219-GP

CPSC Group Project submission 4

We Ran in to some issues as previos iterations of our project were not javafx projects and instead java projects. 
This caused some issues when getting code ont ecclipse and a Ta suggested it would be easiest to start a new 
repository. You can find our old repository in previos submission or by this link
:
package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			FXMLLoader loader = new FXMLLoader();
			VBox root = loader.load(new FileInputStream("src/application/InputView.fxml"));
			RoutePlannerController controller = (RoutePlannerController)loader.getController();
			controller.testStage = primaryStage;
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("RouteFinder");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

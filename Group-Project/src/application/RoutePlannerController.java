package application;

import java.io.IOException;
import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RoutePlannerController {
	Stage testStage;
	
// all the variables we can pull from the first scene
    @FXML
    private TextField xThree;

    @FXML
    private TextField xFour;

    @FXML
    private TextField xTwo;

    @FXML
    private TextField yOne;

    @FXML
    private TextField yFour;

    @FXML
    private TextField max;

    @FXML
    private TextField xOne;

    @FXML
    private TextField yTwo;

    @FXML
    private TextField yThree;

    @FXML
    void FindRoute(ActionEvent event) throws IOException{ 
    	int[][] myArray; // converts the user inputs into integers. these are put into a 2d list we can use for the program to run
        int x1 = Integer.parseInt(xOne.getText());
        int x2 = Integer.parseInt(xTwo.getText());
        int x3 = Integer.parseInt(xThree.getText());
        int x4 = Integer.parseInt(xFour.getText());
        int y1 = Integer.parseInt(yOne.getText());
        int y2 = Integer.parseInt(yTwo.getText());
        int y3 = Integer.parseInt(yThree.getText());
        int y4 = Integer.parseInt(yFour.getText());
        int maximum = Integer.parseInt(max.getText());
        myArray = new int[][] {{x1, x2, x3, x4}, {y1, y2, y3, y4}, {maximum}};
    	//once the user clicks the button to calculate the route this is where the code will run
		//define critital arrays for Main class 
		int[] XvalMain = myArray[0]; 
		int[] YvalMain = myArray[1];
		double maxLengthMain = myArray[2][0];
		//defines arrays well need later: note we did i like this so 
		// so we can eventually do all these operations in a different class

		GetMagnitudes start = new GetMagnitudes();
		start.setXval(XvalMain);//so we can use private
		start.setYval(YvalMain);//parameters
		double [] magnitudesMain = GetMagnitudes.pythag();

		GetSetOfLists sets = new GetSetOfLists();
		sets.magnitudes = magnitudesMain;
		sets.maxLength = maxLengthMain;
		String [][] setOfListsMain = GetSetOfLists.filter();


		PathConnect con = new PathConnect();
		con.setOfLists = setOfListsMain;
		String [][]finalSetOfListsMain = PathConnect.connect();

		GetSetOfLengths connected = new GetSetOfLengths();
		connected.magnitudes = magnitudesMain;
		connected.setOfLists = finalSetOfListsMain;
		double[][] finalSetOfLengthsMain = GetSetOfLengths.filterlengths();

		EfficiencyTest test = new EfficiencyTest();
		test.setOfLists = finalSetOfListsMain;
		test.setOfLengths = finalSetOfLengthsMain;
		String[] bestPathsMain = EfficiencyTest.best();
		System.out.println(Arrays.toString(bestPathsMain));
		
		String stringBestPaths = "";
		for (int i=0 ; i<bestPathsMain.length; i++){
			stringBestPaths += bestPathsMain[i]+ " ";
		}
		Pane pane = new Pane();
		for (int q=0 ; q<bestPathsMain.length; q++){
			if (bestPathsMain[q].equals("AB")) {
			Line line1 = new Line(x1,y1,x2,y2);
			pane.getChildren().addAll(line1);
			}
			if (bestPathsMain[q].equals("AC")) {
			Line line2 = new Line(x1,y1,x3,y3);
			pane.getChildren().addAll(line2);
			}
			if (bestPathsMain[q].equals("AD")) {
			Line line3 = new Line(x1,y1,x4,y4);
			pane.getChildren().addAll(line3);
			}
			if (bestPathsMain[q].equals("BC")) {
			Line line4 = new Line(x2,y2,x3,y3);
			pane.getChildren().addAll(line4);
			}
			if (bestPathsMain[q].equals("BD")) {	
			Line line5 = new Line(x2,y2,x4,y4);
			pane.getChildren().addAll(line5);
			}
			if (bestPathsMain[q].equals("CD")) {
			Line line6 = new Line(x3,y3,x4,y4);
			pane.getChildren().addAll(line6);
			}
    }
		Scene displayScene = new Scene(pane,500,300);
		//Scene displayScene = new Scene(new Label (stringBestPaths));
		testStage.setScene(displayScene);
		//displays second scene the set of best paths
}
}
    

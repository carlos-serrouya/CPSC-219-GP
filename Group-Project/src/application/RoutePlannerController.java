package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class RoutePlannerController {
	Stage testStage;
	
	@FXML
	private Label clickLocationLabel;
	private int[][] clickLocations = new int[3][4];
	private int clickCounter = 0;
	@FXML
	private TextField max;
	@FXML
	private Label maxLengthNumber;

	@FXML
	public void initialize() {
		  maxLengthNumber.setText("0");
	       max.setOnKeyTyped(this::handleMaxKeyTyped);
	}
	@FXML
	private void handleMaxKeyTyped(KeyEvent event) { // updates code in scene to see max length. hoping to use this later for  methods that need it
	    String maxText = max.getText();
	    int maxLength = maxText.length();
	    char typedChar = event.getCharacter().charAt(0);
	    if (Character.isDigit(typedChar)) {
	        maxLengthNumber.setText(String.valueOf(maxLength));
	        //double max1 = Double.parseDouble(maxLengthNumber.getText()); // set max1 to the same value as maxLength
	    }
	}

	@FXML
	void handleMouseClick(MouseEvent event) {
	    //int[][] clickLocations = new int[3][4]; // initialize 2D array to store user clicks
	    // form of the array: the first row contains the X values,
	    // the second row contains the Y values
	    // the third row contains the maximum value.
	    
	    double x = event.getX();
	    double y = event.getY();

	    if (clickCounter < 4) {
	        clickLocations[0][clickCounter] = (int) x;
	        clickLocations[1][clickCounter] = (int) y;
	        clickCounter++;
	    }

	    if (clickCounter == 4) {
	        double max1 = Double.parseDouble(maxLengthNumber.getText()); // set max1 to the same value as maxLength
	        clickLocations[2][0] = (int) max1;
	        // perform calculations using the clickLocations array
	    
	    int[] XvalMain = clickLocations[0]; 
		int[] YvalMain = clickLocations[1];
	    
		double maxLengthMain = clickLocations[2][0];
	    
	    
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
			Scene displayScene = new Scene(new Label (stringBestPaths));
			testStage.setScene(displayScene);
			//displays second scene the set of best paths
	    }
	}
}
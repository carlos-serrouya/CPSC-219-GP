package application;

import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

/** 
 * This controller class takes the 4 locations the user inputs as well as a max length. it then outputs the most efficient path under the maximum length input by the user.
 * @author Romeo Champagne
 */
public class RoutePlannerController {
	Stage testStage;


	@FXML
	private Label clickLocationLabel;
	/**
	 * 2D array of integers that represent the locations where the user has clicked on the scene
	 * The first dimension represents the click event number, and the second dimension
	 * represents the x and y coordinates of the click event.
	 */
	private int[][] clickLocations = new int[3][4];


	/**
	 * tracks how many clicks have happened so far
	 */
	private int clickCounter = 0;

	/**
	 * max length that the user entered
	 */
	@FXML
	private TextField max;

	/**
	 * confirms to the user what they have entered in the scene for max length
	 */
	@FXML
	private Label maxLengthNumber;


	/**
	 * This method is called when the "Find Path" button is clicked on the GUI.
	 * It gets the x and y values of the clicks from clickLocations and 
	 * the maximum length entered. It then proceeds to find the most efficient path
	 * that connects the clicks without exceeding the maximum length.
	 */
	@FXML
	public void FindPathButtonClick() {
		// gets the x and y values from clickLocations
		int[] XvalMain = clickLocations[0]; 
		int[] YvalMain = clickLocations[1];
		
		// getting the double value of the textField entered
		double maxLengthMain;
		try {  
			 maxLengthMain=Double.parseDouble(max.getText());  
		  } 
		catch(NumberFormatException e){  
		    return;  
		}

		// sets the x and y values for the calculations we need

		//calculates the magnitudes of the distances between the points
		FindMagnitudes start = new FindMagnitudes();

		// all the variables we can pull from the first scene




		start.setXval(XvalMain);//so we can use private
		start.setYval(YvalMain);//parameters
		double [] magnitudesMain = FindMagnitudes.getPythag();

		FindArrayOfLists sets = new FindArrayOfLists();
		sets.setMagnitudes (magnitudesMain);
		sets.setMaxLength (maxLengthMain);
		String [][] arrayOfListsMain = FindArrayOfLists.getFilter();

		PathConnect con = new PathConnect();
		con.setArrayOfLists (arrayOfListsMain);
		String [][]finalSetOfListsMain = PathConnect.getConnect();

		FindArrayOfLengths connected = new FindArrayOfLengths();
		connected.setMagnitudes(magnitudesMain);
		connected.setArrayOfLists(finalSetOfListsMain);
		double[][] finalSetOfLengthsMain = FindArrayOfLengths.getFilterLengths();

		EfficiencyTest test = new EfficiencyTest();
		test.setArrayOfLists (finalSetOfListsMain);
		test.setArrayOfLengths (finalSetOfLengthsMain);
		String[] bestPathsMain = EfficiencyTest.getBest();
		
		System.out.println(Arrays.toString(bestPathsMain));

		SceneTwo display = new SceneTwo();
		display.setXval(XvalMain);
		display.setYval(YvalMain);
		display.setBestPaths(bestPathsMain);
		Pane paneMain = SceneTwo.getPane();
		Scene displayScene = new Scene(paneMain,800,800);
		//Scene displayScene = new Scene(new Label (stringBestPaths));
		testStage.setScene(displayScene);
		//displays second scene the set of best paths

	}



	/**
	 * the next 2 methods work together. They are mostly for readability but also add some complexity. 
	 * The first method sets the number beside the max length input box as 0
	 * When a key is typed in the box, the MaxKeyTyped method will e called.
	 */

	@FXML
	public void initialize() {

		maxLengthNumber.setText("0");
		max.setOnKeyTyped(this::MaxKeyTyped);
	}

	/**
	 * When key is typed in the max length box, this method is called. 
	 * It will update the wording beside the maxLength input box to show what the user has input.
	 * 
	 */
	@FXML
	private void MaxKeyTyped(KeyEvent event) { // updates code in scene to see max length

		String maxText = max.getText();
		int maxLength = maxText.length();
		char typedChar = event.getCharacter().charAt(0);
		// has to be a digit to get updated
		if (Character.isDigit(typedChar)) {
			maxLengthNumber.setText(maxText);

		}
	}
	/**
	 * The method is important. It is called when the user clicks on the map on the first scene.
	 * As the user selects the location of their points on the map, the method is 
	 * called an will update the clickLocations array. 
	 * It does this until it has counted 4 clicks by the user.
	 * once 4 clicks are recorded it will allow the rest of the code to function
	 * 
	 */

	@FXML
	void MouseClick(MouseEvent event) {

		double x = event.getX();
		double y = event.getY();
		// records the locations of the dots in an x and y axis
		if (clickCounter < 4) {
			clickLocations[0][clickCounter] = (int) x;
			clickLocations[1][clickCounter] = (int) y;
			clickCounter++;
		}

		if (clickCounter == 4) {
			double max1 = Double.parseDouble(maxLengthNumber.getText()); // set max1 to the same value as maxLength
			clickLocations[2][0] = (int) max1;
		}

	}   
}


package application;

import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

/** 
 * This controller class takes the 4 locations the user inputs as well as a max length. it then outputs the most efficient path under the maximum length input by the user.
 * @author Romeo, Carlos
 */
public class RoutePlannerController {
	
	/**
	 * the main stage of the application.
	 */
	Stage testStage;
	
	/**
	 * 2D array of integers that represent the locations where the user has clicked on the scene
	 * The first dimension represents the click event number, and the second dimension
	 * represents the x and y coordinates of the click event.
	 */
	@FXML
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
	 * Label used to show errors in the input max length from the user
	 */
	@FXML
	private Label errorDisplay;
	
	/**
	 * Label used to show errors in the input locations from the user
	 */
	@FXML
	private Label clickErrors;
	
	/**
	 * Button used to move to the next scene, also start the calculations
	 */
	@FXML
	private  Button findPathButton;
	
    /**
     * based on the error caused by the user, this code will change the errorDisplay label on the GUI
     * 
     * @param message the message that will be shown by the errorDisplay label in the GUI
     */
	public void changeErrorDisplay(String message) {
	    errorDisplay.setText(message);
	}
	
	/**
     * based on the location error caused by the user, this code will change the clickErrors label on the GUI
     * 
     * @param message the message that will be shown by the clickErrors label in the GUI
     */
	public void changeclickErrors(String message) {
		clickErrors.setText(message);
	}

	/**
	 * Looks at the clickErrors and errorDisplay messages
	 * depending on what it sees, it will enable the button
	 */
	public void keyReleasedProperty() {
		String errorText = errorDisplay.getText();
	    String clickErrorsText = clickErrors.getText();
		boolean isDisabled = (!(errorText.equals("Proper max length") && clickErrorsText.equals("locations are ok")));
		findPathButton.setDisable(isDisabled);
	}
	
	/**
	 * This method is called when the "Find Path" button is clicked on the GUI.
	 * It gets the x and y values of the clicks from clickLocations and 
	 * the maximum length entered. It then proceeds to find the most efficient path
	 * that connects the clicks without exceeding the maximum length.
	 * It will show the best path in the next scene
	 * @param XvalMain this is an array of the x values of the clicks from the user
	 * @param YvalMain this is an array of the y values of the clicks from the user
	 * @param maxLengthMain this is the maximum length the user put in
	 */
	@FXML
	public void FindPathButtonClick() {
		
		// gets the x and y values from clickLocations
		int[] XvalMain = clickLocations[0]; 
		int[] YvalMain = clickLocations[1];
		
		// this section tries to convert the "max" value entered by the user to a double
		// if it can't convert to a double it will not fill the maxLengthMain value
		//It will only allow numbers to be entered
		double maxLengthMain;
		try {  
			 maxLengthMain=Double.parseDouble(max.getText());  
		  } 
		catch(NumberFormatException e){  
		    return;  
		}

		//makes an object for the FindMagnitudes class
		FindMagnitudes start = new FindMagnitudes();
		// sets the x and y values into a new array
		start.setXval(XvalMain);
		start.setYval(YvalMain);
		// array of doubles with the distances between points
		double [] magnitudesMain = FindMagnitudes.getPythag();

		//makes an object for the FindArrayofLists class
		FindArrayOfLists sets = new FindArrayOfLists();
		sets.setMagnitudes (magnitudesMain); //setting magnitudes to the object
		sets.setMaxLength (maxLengthMain); // setting max length to the object
		String [][] arrayOfListsMain = FindArrayOfLists.getFilter(); //makes an array list of all possible paths under max length
		
		//makes an object for the PathConnect class
		PathConnect con = new PathConnect();
		con.setArrayOfLists (arrayOfListsMain); // sets the array lists of filtered paths to the object
		String [][]finalSetOfListsMain = PathConnect.getConnect();//finds connected paths between the points
		
		//makes an object for the FindArrayofLengths class
		FindArrayOfLengths connected = new FindArrayOfLengths();
		connected.setMagnitudes(magnitudesMain); // setting magnitudes to the object
		connected.setArrayOfLists(finalSetOfListsMain); // setting the connected paths under the max length to the object
		double[][] finalSetOfLengthsMain = FindArrayOfLengths.getFilterLengths(); //filters the array of lengths
		
		//makes an object for the EfficiencyTest class
		EfficiencyTest test = new EfficiencyTest();
		test.setArrayOfLists (finalSetOfListsMain);
		test.setArrayOfLengths (finalSetOfLengthsMain);
		String[] bestPathsMain = EfficiencyTest.getBest(); //gives us the best paths
		
		//System.out.println(Arrays.toString(bestPathsMain)); //prints the best paths in the console
		
		SceneTwo display = new SceneTwo();
		display.setXval(XvalMain);
		display.setYval(YvalMain);
		display.setBestPaths(bestPathsMain);
		Pane paneMain = SceneTwo.getPane();
		Scene displayScene = new Scene(paneMain,800,800);
		testStage.setScene(displayScene);
		//shows the second scene with the best paths illustrated

	}

	/**
	 * the next 2 methods work together. They are mostly for readability but also add some complexity. 
	 * The first method sets the number beside the max length input box as 0
	 * When a key is typed in the box, the MaxKeyTyped method will e called.
	 */
	@FXML
	public void initialize() {
		findPathButton.setDisable(true);
		maxLengthNumber.setText("0");
		max.setOnKeyTyped(this::MaxKeyTyped);
		// listens to what the user inputs into the max length field
	    max.textProperty().addListener((observable, oldValue, newValue) -> {
	        try {
	            int num = Integer.parseInt(newValue);
	            if (num > 0) {
	                changeErrorDisplay("Proper max length"); // needs the value to be above 0 and a number
	                
	            } else {
	                changeErrorDisplay("Error: Not a positive number"); // input was negative or 0
	                
	            }
	        } catch (NumberFormatException e) {
	            changeErrorDisplay("Error: Not a number");// input was not a number
	        }
	    });
	}
	
	/**
	 * When key is typed in the max length box, this method is called. 
	 * It will update the wording beside the maxLength input box to show what the user has input.
	 * @param event KeyEvent, from when the user keys in the the max length
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
	 * The method is called when the user clicks on the map on the first scene.
	 * As the user selects the location of their points on the map, the method is 
	 * called an will update the clickLocations array. 
	 * It does this until it has counted 4 clicks by the user.
	 * once 4 clicks are recorded it will allow the rest of the code to function
	 * 
	 * @param event MouseEvent, from the mouse click on the gui
	 */
	@FXML
	void MouseClick(MouseEvent event) {

		double x = event.getX();
		double y = event.getY();
		// records the locations of the clicks in an x and y axis
		if (clickCounter < 4) {
			clickLocations[0][clickCounter] = (int) x;
			clickLocations[1][clickCounter] = (int) y;
			clickCounter++;
			changeclickErrors("not enough location inputs"); // error handling due to not enough points put into the system
			
		}
			double max1 = Double.parseDouble(maxLengthNumber.getText()); // set max1 to the same value as maxLength
			clickLocations[2][0] = (int) max1;
			
		
		if (clickCounter == 4) {
				changeclickErrors ("locations are ok");
				//this is for error handling, once we have 4 clicks it will show this
		}   
	}
}

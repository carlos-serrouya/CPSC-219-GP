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

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

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
		 double maxLengthMain = Double.parseDouble(max.getText());
			
		// sets the x and y values for the calculations we need
		
		//calculates the magnitudes of the distances between the points
		FindMagnitudes start = new FindMagnitudes();
    
// all the variables we can pull from the first scene

    @FXML
    void FindRoute(ActionEvent event) throws IOException{ 

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


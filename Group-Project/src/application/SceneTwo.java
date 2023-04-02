package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class SceneTwo {
	
	private static String [] bestPaths;
	private static int [] xVal;
	private static int [] yVal;
	
	private static Pane makeSceneTwo () {
		
		int x1 = xVal[0];
		int x2 = xVal[1];
		int x3 = xVal[2];
		int x4 = xVal[3];
		int y1 = yVal[0];
		int y2 = yVal[1];
		int y3 = yVal[2];
		int y4 = yVal[3];
		
		Pane pane = new Pane();
		Image map = new Image("file:///C:/Users/CS219-user/git/CPSC-219-GP/Group-Project/src/application/SmallerScaleCalgary.png");
		ImageView iv = new ImageView(map);
		iv.setFitWidth(555);
		iv.setFitHeight(555);
		pane.getChildren().add(iv);
		for (int q=0 ; q<bestPaths.length; q++){
			
			
			if (bestPaths[q].equals("AB")) {
				Line line1 = new Line(x1,y1,x2,y2);
				pane.getChildren().addAll(line1);
			}
			if (bestPaths[q].equals("AC")) {
				Line line2 = new Line(x1,y1,x3,y3);
				pane.getChildren().addAll(line2);
			}
			if (bestPaths[q].equals("AD")) {
				Line line3 = new Line(x1,y1,x4,y4);
				pane.getChildren().addAll(line3);
			}
			if (bestPaths[q].equals("BC")) {
				Line line4 = new Line(x2,y2,x3,y3);
				pane.getChildren().addAll(line4);
			}
			if (bestPaths[q].equals("BD")) {	
				Line line5 = new Line(x2,y2,x4,y4);
				pane.getChildren().addAll(line5);
			}
			if (bestPaths[q].equals("CD")) {
				Line line6 = new Line(x3,y3,x4,y4);
				pane.getChildren().addAll(line6);
			}
		}
		
		return pane;
	}
	
	public static Pane getPane() {
		return SceneTwo.makeSceneTwo();
	}
	
	public int[] getXval (){ // getter
		return xVal;
	}

	/**
	 * Gets the Yval array.
	 * @return An integer array representing the y coordinates of the 4 Points.
	 */
	public int[] getYval (){ // getter        
		return yVal;            
	}
	/**
	 * Gets the bestPaths array.
	 * @return An integer array representing the y coordinates of the 4 Points.
	 */
	
	public String [] getBestPaths() {
		return bestPaths;
	}

	/**
	 * Sets the Xval array to a new array.
	 * @param setX An integer array representing the new x coordinates of the 4 pts.
	 */
	public void setXval(int[] setX) { // setter
		xVal = setX;        
	}

	/**
	 * Sets the Yval array to a new array.
	 * @param setY An integer array representing the new y coordinates of the four pts
	 **/
	public void setYval(int[] setY) { //setter
		yVal = setY;

	}
	
	/**
	 * Sets the bestPaths array to a new array.
	 * @param setY An integer array representing the new y coordinates of the four pts
	 **/
	public void setBestPaths(String [] setBest) {
		bestPaths = setBest;
	}
		
}

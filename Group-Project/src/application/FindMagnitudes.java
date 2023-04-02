package application;

class PointOne {
	int x;
	int y;
	String name;
}
/**
 * explain..
 * @author CS219-user
 *
 */
public class FindMagnitudes {


	private static int[] Xval;
	private static int[] Yval;

	/**
	 * Contains a static method pythag() that takes 4 integer arrays Xval and Yval representing the x and y coordinates of 4 Points, 
	 * and returns an array of doubles containing the magnitudes (distances) between every pair of Points.
	 * Also contains a public static method getPythag() that returns the result of pythag() method.
	 * Contains getter and setter methods getXval() and setXval() for the Xval array, and getYval() and setYval() for the Yval array.
	 * Author Natalie Thain
	 */

	private static double[] pythag() {
		/**
		 * Calculates the magnitudes (distances) between every pair of Points represented by the Xval and Yval arrays.
		 * returns An array of doubles containing the magnitudes (distances) between every pair of Points.
		 */
		PointOne[] newArr = new PointOne[4];

		char names = 'A';
		// function where you are given x,y
		for (int i = 0; i < 4; i++) {
			newArr[i] = new PointOne();
			newArr[i].x = Xval[i];
			newArr[i].y = Yval[i];
			newArr[i].name = names + "";
			names++;
		}

		int amountOfPoints = 4;
		double[] magnitudes = new double[amountOfPoints * (amountOfPoints - 1) / 2]; // create an array to store the magnitudes
		int index = 0;
		// finding all vectors and calculating magnitudes
		for (int i = 0; i < amountOfPoints - 1; i++) {
			for (int j = i + 1; j < amountOfPoints; j++) {

				double magnitude = Math.sqrt(Math.pow((newArr[j].x - newArr[i].x), 2)
						+ Math.pow((newArr[j].y - newArr[i].y), 2)); // calculate magnitude
				magnitudes[index] = magnitude; // store magnitude in the array
				index++;

			}
		}

		return magnitudes;
	}

	/**
	 * Returns the result of the pythag() method.
	 * @return An array of doubles containing the magnitudes (distances) between every pair of Points.
	 */
	public static double[] getPythag() {
		return FindMagnitudes.pythag();
	}

	/**
	 * Gets the Xval array.
	 * @return An integer array representing the x coordinates of the 4 Points.
	 */
	public int[] getXval (){ // getter
		return Xval;
	}

	/**
	 * Gets the Yval array.
	 * @return An integer array representing the y coordinates of the 4 Points.
	 */
	public int[] getYval (){ // getter        
		return Yval;            
	}

	/**
	 * Sets the Xval array to a new array.
	 * @param setX An integer array representing the new x coordinates of the 4 pts.
	 */
	public void setXval(int[] setX) { // setter
		Xval = setX;        
	}

	/**
	 * Sets the Yval array to a new array.
	 * @param setY An integer array representing the new y coordinates of the four pts
	 **/
	public void setYval(int[] setY) { //setter
		Yval = setY;

	}
}
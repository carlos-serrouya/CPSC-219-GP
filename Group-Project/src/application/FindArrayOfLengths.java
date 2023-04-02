package application;

import java.util.Arrays;

/**
 * This class represents a utility for filtering and 
 * processing sets of paths, in order to obtain a
 * corresponding 2D array of lengths. The lengths are calculated 
 * based on the magnitudes of the distances between pts,
 * @author Romeo
 *
 */

public class FindArrayOfLengths {
	
	// declaring private static variables
	private static double [] magnitudes;
	private static String [][] arrayOfLists;
	
	/**
	* This method takes the set of paths that fit previous restrictions and makes a 2d array using the 
	* corresponding magnitudes. It returns the 2d array setOfLengths.
	* @return double[][] - a 2D array containing the set of lengths
	*/
	private static double[][]  filterLengths(){
		// declaring and initializing an empty 2D array
		double [][] setOfLengths = {};

		// looping through the arrayOfLists array
		for (int t = 0; t < arrayOfLists.length; t++) {
			// retrieving the temporary set
			String[] tempSet = arrayOfLists[t];
			// declaring and initializing a temporary length array
			double[] tempLength = new double[tempSet.length];

			// looping through the temporary set
			for (int q = 0; q < tempSet.length; q++) {
				// matching corresponding magnitudes to path and adding to 1D array
				if (tempSet[q].equals("AB")) {
					tempLength[q] = magnitudes[0];
				}

				if (tempSet[q].equals("AC")) {
					tempLength[q] = magnitudes[1];

				}

				if (tempSet[q].equals("AD")) {
					tempLength[q] = magnitudes[2];
				}

				if (tempSet[q].equals("BC")) {
					tempLength[q] = magnitudes[3];
				}

				if (tempSet[q].equals("BD")) {
					tempLength[q] = magnitudes[4];
				}

				if (tempSet[q].equals("CD")) {
					tempLength[q] = magnitudes[5];
				}        			
			}
			
			// copying setOfLengths to a new 2D array
			double[][] newsetOfLengths = Arrays.copyOf(setOfLengths, setOfLengths.length + 1);
			// deep copy
			newsetOfLengths[newsetOfLengths.length - 1] = tempLength;
			// assigning newsetOfLengths to setOfLengths
			setOfLengths = newsetOfLengths;
			// adding the 1D array to the 2D array
		}

		return setOfLengths;
	}
	
	/**
	* This method is a getter method for the filterLengths method.
	* 
	* @return double[][] - a 2D array containing the set of lengths
	*/
	public static double [][] getFilterLengths() {
		return FindArrayOfLengths.filterLengths();
	}
	
	/**
	* This method sets the magnitudes variable.
	* @param setMagnitudesVar - an array of doubles representing the magnitudes
	*/
	public void setMagnitudes (double [] setMagnitudesVar) {
		magnitudes = setMagnitudesVar;		
	}
	
	/**
	* This method sets the arrayOfLists variable.
	* @param setArrayOfListsVar - a 2D array of strings representing the lists of paths
	*/
	public void setArrayOfLists (String [][] setArrayOfListsVar) {
		arrayOfLists = setArrayOfListsVar;
	}
	
	/**
	* Returns the magnitudes of the edges in the graph, as an array of doubles. These magnitudes are used to calculate
	* the lengths of the paths between pts, which are returned by the getFilterLengths() method.
	* @return the magnitudes of the length between pts, as an array of doubles.
	*/
	
	public double[] getMagnitudes () {
		return magnitudes;
	}
	
	/**
	Returns the sets of paths that were provided to the setArrayOfLists(String[][]) method. These sets of paths
	are used to calculate the lengths of the paths between pts, which are returned by the {@link #getFilterLengths()} method.
	@return the sets of paths, as a 2D array of strings.
	*/
	
	public String [][] setArrayOfLists(){
		return arrayOfLists;
	}


}
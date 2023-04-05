package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The FindArrayOfLists class contains methods to filter out paths from a powerset that are not connective
 * and meet user input restrictions. It also has getter and setter methods for the magnitudes and max length variables.
 * @author RomeChampagne
 */

public class FindArrayOfLists {

	private static double[] magnitudes;
	private static double maxLength;

	/**
	 * The filter method tests to see if the different sets of paths in a powerset follow the user input restrictions,
	 * and filters out some paths that arent connective.
	 * @return The method returns a 2D String array of path sets that are connective and meet user input restrictions
	 */

	private static String[][] filter() {

		double conversion = 46.855462;
		maxLength = maxLength/conversion; //converts maxLength from inputted meters to pixels
		
		String [][] powerSetArray = powerSet();
		
		String [][] setOfLists = {};

		for (int t = 0; t < powerSetArray.length; t++) {

			String[] tempSet = powerSetArray[t];

			if (tempSet.length >= 3) {
				//if this isnt true the paths wont be path connected 


				double totalLength = 0; // initialize total length of paths to zero

				for (int q = 0; q < tempSet.length; q++) {
					//creates 2 arrays. one string made of the paths. on made of the distances of those paths

					if (tempSet[q].equals("AB")) {
						totalLength += magnitudes[0];
					}

					if (tempSet[q].equals("AC")) {
						totalLength += magnitudes[1];
					}

					if (tempSet[q].equals("AD")) {
						totalLength += magnitudes[2];
					}

					if (tempSet[q].equals("BC")) {
						totalLength += magnitudes[3];
					}

					if (tempSet[q].equals("BD")) {
						totalLength += magnitudes[4];
					}

					if (tempSet[q].equals("CD")) {
						totalLength += magnitudes[5];
					}        			
				}
				//previos block of code tests to see if temp set is under max length
				//note that wile the code looks similar to findArrayOfLengths there 
				//are small differences that would make having them done by the same
				//method difficult
				if (totalLength <= maxLength) {
					//if the 1d array is under max length it adds the 1d array to a 2d array which will be passed on

					String[][] newsetOfLists = Arrays.copyOf(setOfLists, setOfLists.length + 1);
					//deep copy
					newsetOfLists[newsetOfLists.length - 1] = tempSet;

					setOfLists = newsetOfLists;



				}

			}

		}
		return setOfLists;
	}

	private static String [][] powerSet(){
		String [] originalSetString = {"AB","AC","AD","BC","BD","CD"};
		/**
		 * this method makes a powersetof originalSetString
		 * than converts the list to an array
		 * @return String [][] powerSetArray
		 */


		//CITATION: the following 11 lines of code are modified from stack overflow author: Andrew Mao
		//LINK: https://stackoverflow.com/questions/1670862/obtaining-a-powerset-of-a-set-in-java        

		List<String> list = new ArrayList<String>();
		for (String element : originalSetString) {
			list.add(element);
		}

		int n = list.size();

		Set<Set<String>> powerSetString = new HashSet<Set<String>>();

		for( long i = 0; i < (1 << n); i++) {
			Set<String> element = new HashSet<String>();
			for( int j = 0; j < n; j++ )
				if( (i >> j) % 2 == 1 ) element.add(list.get(j));
			powerSetString.add(element); 
		}

		//created power set


		String[][] powerSetArray = new String[powerSetString.size()][];
		int i = 0;
		for (Set<String> set : powerSetString) {
			powerSetArray[i] = new String[set.size()];
			int j = 0;
			for (String str : set) {
				powerSetArray[i][j] = str;
				j++;
			}
			i++;
		}
		//turns power set into an array

		return powerSetArray;
	}

	/**
	 * Returns the value of the private static instance variable "filter".
	 * @return A two-dimensional array of String type.
	 */

	public static String [][] getFilter(){
		return FindArrayOfLists.filter();
	}
	/**
	 * Sets the value of the private instance variable "magnitudes".
	 * @param setMagnitudesVar A double array representing the new value of magnitudes.
	 */

	public void setMagnitudes (double [] setMagnitudesVar){
		magnitudes = setMagnitudesVar;
	}

	/**
	 * Sets the value of the private instance variable "maxLength".
	 * @param setMaxLengthVar A double representing the new value of maxLength.
	 */

	public void setMaxLength (double setMaxLengthVar) {
		maxLength = setMaxLengthVar;
	}

	/**
	 * getter
	 * Returns the value of the private instance variable "magnitudes".
	 * @return A double array representing magnitudes.
	 */

	public double [] getMagnitudes () {
		return magnitudes;
	}

	/**
	 *Returns the value of the private instance variable "maxLength".
	 * @return A double representing maxLength.
	 */

	public double getMaxLength () {
		return maxLength;
	}
}

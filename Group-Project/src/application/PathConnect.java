package application;

import java.util.Arrays;
/**
 * Author Carlos
 * The PathConnect class contains methods for testing if paths in a set of paths are path connected.
 * It provides functionality for determining all the path connected sets in a given set of paths.
 */
public class PathConnect {

	private static String [][] arrayOfLists;

	private static String[][] connect() {
		/**
		 * The connect method tests if paths in set of paths in arrayOfLists are path connected. If a set of
		 * paths is path connected, it is added to the finalSetOfLists array. The method returns an array of all
		 * path connected sets in the set of paths.
		 * @return An array of all path connected sets in the set of paths.
		 */

		String[][] finalSetOfLists = {};
		int numOfLists = arrayOfLists.length;

		String[] listOfPoints = {"A", "B", "C", "D"};
		int numOfPoints = listOfPoints.length;


		for (int q = 0; q < numOfLists; q++) {
			String[] paths = arrayOfLists[q];
			int numOfPaths = paths.length;

			//tests every set in 2d array

			//here we test for path connectivness. if theres 4 points and three or more 
			//paths we know they are path connected if and only if each point shows up at least once
			boolean allPathsConnected = true;
			for (int i1 = 0; i1 < numOfPoints; i1++) {
				boolean pathConnected = false;
				for (int t = 0; t < numOfPaths; t++) {
					if (paths[t].charAt(0) == listOfPoints[i1].charAt(0) || paths[t].charAt(1) == listOfPoints[i1].charAt(0)) {
						pathConnected = true;
						break;
					}
				}
				if (!pathConnected) {
					allPathsConnected = false;
					break;
				}
			}
			if (allPathsConnected) {

				String[][] newFinalSetOfLists = Arrays.copyOf(finalSetOfLists, finalSetOfLists.length + 1);
				//deep copy
				newFinalSetOfLists[newFinalSetOfLists.length - 1] = paths;

				finalSetOfLists = newFinalSetOfLists;


			}
		}
		return finalSetOfLists;
	}

	/**
	 * The getConnect method returns an array of all path connected sets in the set of paths.
	 * @return An array of all path connected sets in the set of paths.
	 */

	public static String[][] getConnect() {
		return PathConnect.connect();	
	}

	/**
	 * The setArrayOfLists method sets the set of paths to be used for testing path connectivity.
	 * @param setArrayOfListsVar The set of paths to be used for testing path connectivity.
	 */

	public void setArrayOfLists(String [][] setArrayOfListsVar) {
		arrayOfLists = setArrayOfListsVar;
	}

	/**
	 * The getArrayOfLists method returns the set of paths to be used for testing path connectivity.
	 * @return The set of paths to be used for testing path connectivity.
	 */

	public String [][] getArrayOfLists() {
		return arrayOfLists;
	}

}
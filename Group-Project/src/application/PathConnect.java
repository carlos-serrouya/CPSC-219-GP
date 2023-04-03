package application;

import java.util.Arrays;
/**
 * Author Carlos
 * The PathConnect class contains methods for testing if paths in a set of paths are path connected.
 * It provides functionality for determining all the path connected sets in a given set of paths.
 * uses the abstract method from the abstract class Or interface TestingConnect
 */
public class PathConnect implements TestingConnect {

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

		for (int q = 0; q < numOfLists; q++) {
			String[] paths = arrayOfLists[q];

			PathConnect path = new PathConnect();
			boolean allPathsConnected = path.oneDArrayConnect(paths);
			//calls method that tests if the given 1d array is connected

			if (allPathsConnected) {
				// if it is connected it adds to new list
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

	/**
	 * From my understanding of interfaces and encapsulation I believe this has to
	 * be public but let me know if I am breaking encapsulation requirements
	 * Tests if the given set of paths is path-connected.
	 * @param paths an array of strings representing the paths to be tested
	 * @return true if the paths are path-connected, false otherwise
	 * The method tests if there are at least 4 distinct points in the set of paths and if each point is
	 * connected to at least one other point. If both conditions are met, the method returns true, indicating
	 * that the set of paths is path-connected. Otherwise, the method returns false.
	 */

	public boolean oneDArrayConnect(String [] paths) {
		int numOfPaths = paths.length;
		String[] listOfPoints = {"A", "B", "C", "D"};
		int numOfPoints = listOfPoints.length;
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
		return allPathsConnected;
	}

}
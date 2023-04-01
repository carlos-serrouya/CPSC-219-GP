package application;

import java.util.Arrays;

public class PathConnect {

	private static String [][] arrayOfLists;

	private static String[][] connect() {
  // tests if paths in set of paths in setOfLists are path connected
   // with 4 pts this is easy. every path is path connected if and only if 
    // theres a path going to every point
    //returns all the path connected sets
    //author Carlos
    
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
	public static String[][] getConnect() {
		return PathConnect.connect();
		
	}
	
	
	
	public void setArrayOfLists(String [][] setArrayOfListsVar) {
		arrayOfLists = setArrayOfListsVar;
	}
	
	public String [][] getArrayOfLists() {
		return arrayOfLists;
	}
  
}

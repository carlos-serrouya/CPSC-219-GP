package application;

/**
 * This class contains a method that will take the set of possible path combinations that follow our previous restrictions
 * and output the most efficient path as a string array called bestPaths.
 *
 * Author: Carlos Serrouya
 */

public class EfficiencyTest {

    private static String[][] arrayOfLists;
    private static double[][] arrayOfLengths;


    /**
     * Finds the most efficient path among the set of possible paths.
     * note that i did break this method up as requested but
     * each block of code relies on so many previous variables that
     * breaking it up anymore would actually make the code more complicated
     * @return The most efficient path as a string array.
     */
    private static String[] best() {

        String[] maxPaths = {"AB","AC","AD","BC","BD","CD"};
        // This array defines all possible paths between points A, B, C and D.

        double bestEfficiency = Double.MAX_VALUE;
        // Initially set the best efficiency to the maximum possible value, to ensure any new value will be smaller.
        String[] bestPaths = {"There is no connected path that satisfies the input requirements"};
        // This will be the default value if no paths satisfy the input requirements.
        int numOfLists = arrayOfLists.length;

        for (int q = 0; q < numOfLists; q++) {
            String[] paths = arrayOfLists[q];
            double[] lengths = arrayOfLengths[q];
            double efficiency = Double.MAX_VALUE;
            // Again, initially set the efficiency to the maximum possible value.
            efficiency = 0;
            // The efficiency will be accumulated throughout the loop.
            for (int i1 = 0; i1 < maxPaths.length; i1++) {
                String getPoints = maxPaths[i1];
                char startPoint = getPoints.charAt(0);
                char endPoint = getPoints.charAt(1);
                boolean done = false;
                // This flag is used to determine whether the path from the start to end point has been found.

                if (paths.length == 3) {
                    // If there are only 3 paths, there can only be one way to travel all 4 points in the shortest path.
                    efficiency = threePaths(lengths, efficiency);
                    break;
                }

                for (int t = 0; t < paths.length; t++) {
                    String direct = "" + startPoint + endPoint;
                    // This is used to compare each path to the direct path from the start to end point.
                    if (paths[t].equals(direct)) {
                        // If the path is the direct path, it is always the shortest.
                        efficiency += lengths[t];
                        done = true;
                        break;
                    }
                }

                int count = 0;
                String Astart = "" + "A" + startPoint;
                String startB = "" + startPoint + "B";
                String Bstart = "" + "B" + startPoint;
                String Cstart = "" + "C" + startPoint;
                String startC = "" + startPoint + "C";
                String startD = "" + startPoint + "D";
                // These strings represent the different possible paths from the start point.

				if (!done) {
					for (int t = 0; t < paths.length; t++) {
						
						/**
						 * this block tests how many times is the start point in paths
						 */
						
						if (paths[t].equals(startB)) {
							count += 1;
						}

						if (paths[t].equals(startC)) {
							count += 1;
						}

						if (paths[t].equals(startD)) {
							count += 1;

						}

						if (paths[t].equals(Astart)) {
							count += 1;
						}

						if (paths[t].equals(Bstart)) {
							count += 1;
						}

						if (paths[t].equals(Cstart)) {
							count += 1;
						}
					}

				}

				//currently testing one element in maxpaths ex. CD
				char currentPoint = startPoint;

				if (count == 1 && !done) {
					
					//if startpt in path once and no direct path to end
					//means we have to follow that path	

					for (int t = 0; t < paths.length; t++) {
						//only one of these should be true
						//follows the path from startpt to endpt
						
						if (paths[t].equals(Astart)) {
							efficiency += lengths[t];
							currentPoint = 'A';
							break;
						}

						if (paths[t].equals(startB)) {
							efficiency += lengths[t];
							currentPoint = 'B';
							break;
						}

						else if (paths[t].equals(startC)) {
							efficiency += lengths[t];
							currentPoint = 'C';
							break;
						}

						else if (paths[t].equals(startD)) {
							efficiency += lengths[t];
							currentPoint = 'D';
							break;
						}

						else if (paths[t].equals(Bstart)) {
							efficiency += lengths[t];
							currentPoint = 'B';
							break;
						}

						else if (paths[t].equals(Cstart)) {
							efficiency += lengths[t];
							currentPoint = 'C';
							break;
						}
					}



					while(!done) {



						String midEnd = "" + currentPoint + endPoint;
						String endMid = "" + endPoint + currentPoint;

						for (int t = 0; t < paths.length; t++) {

							if ((paths[t].equals(midEnd))||(paths[t].equals(endMid))) {
								//if our new point has a direct path to end
								efficiency += lengths[t];
								done = true;
								break;	
								//always true if start pt is in once and theres more than 3 paths and no direct path
							}
						}

					}
				}

				if (count == 2 && !done){
					//tests if there 2 paths to end
					//following code will follow the 2 paths and test which one is shorter
					
					char tstPtOne = startPoint;
					char tstPtTwo = startPoint;
					double tstOne = Double.MAX_VALUE;
					double tstTwo = Double.MAX_VALUE;

					for (int t = 0; t < paths.length; t++) {
						//two of these will be true

						if (paths[t].equals(Astart)) {
							if (tstPtOne == startPoint) {
								tstOne = lengths[t];
								tstPtOne = 'A';
							}

							else {
								tstTwo = lengths[t];
								tstPtTwo = 'A';
								break;
							}

						}

						if (paths[t].equals(startB)) {
							if (tstPtOne == startPoint) {
								tstOne = lengths[t];
								tstPtOne = 'B';
							}

							else {
								tstTwo = lengths[t];
								tstPtTwo = 'B';
								break;
							}

						}	

						if (paths[t].equals(Bstart)) {
							if (tstPtOne == startPoint) {
								tstOne = lengths[t];
								tstPtOne = 'B';
							}

							else {
								tstTwo = lengths[t];
								tstPtTwo = 'B';
								break;
							}

						}

						if (paths[t].equals(startC)) {
							if (tstPtOne == startPoint) {
								tstOne = lengths[t];
								tstPtOne = 'C';
							}
							else {
								tstTwo = lengths[t];
								tstPtTwo = 'C';
								break;
							}
						}		    			
						if (paths[t].equals(startD)) {
							if (tstPtOne == startPoint) {
								tstOne = lengths[t];
								tstPtOne = 'D';
							}
							else {
								tstTwo = lengths[t];
								tstPtTwo = 'D';
								break;
							}
						}

						if (paths[t].equals(Cstart)) {
							if (tstPtOne == startPoint) {
								tstOne = lengths[t];
								tstPtOne = 'C';
							}

							else {
								tstTwo = lengths[t];
								tstPtTwo = 'C';
								break;
							}

						}

					}

					String tstOneEnd = "" + tstPtOne + endPoint;
					String tstEndOne = "" + endPoint + tstPtOne;
					String tstTwoEnd = "" + tstPtTwo + endPoint;
					String tstEndTwo = "" + endPoint + tstPtTwo;
					boolean tstOneWork = false;
					boolean tstTwoWork = false;
					//creates new paths to test for
					
					for (int t = 0; t < paths.length; t++) {

						if ((paths[t].equals(tstOneEnd))||(paths[t].equals(tstEndOne))){
							tstOne += lengths[t];
							tstOneWork = true;
						}

						if ((paths[t].equals(tstTwoEnd))||(paths[t].equals(tstEndTwo))){
							tstTwo += lengths[t];
							tstTwoWork = true;
						}    				
					}
					if (!tstOneWork) {
						tstOne = Double.MAX_VALUE;
					}

					if (!tstTwoWork) {
						tstTwo = Double.MAX_VALUE;
					}
					//if one path leads to a dead end we know it wont be shortest path
					
					if (tstOne < tstTwo) {
						efficiency += tstOne;
						done = true;
						//if theres two paths compare the paths
					}
					else {
						efficiency += tstTwo;
						done = true;
					}
				}
			}



			if (efficiency<bestEfficiency) {
				//updates best efficiency

				bestEfficiency = efficiency;
				bestPaths = paths;

			}	 
		}
		return bestPaths;
	}
	private static double threePaths(double [] lengths, double efficiency) {
		for (int t = 0; t < 3; t++) {
			efficiency += 3*lengths[t];
		}
		return efficiency;
		
	}
	
	/**

	Returns the best value from the best() method.
	@return a String array containing the best paths.
	*/
	
	public static String [] getBest (){
		return best();
	}
	
	/**

	Sets the 2D array of Strings to the provided value.
	@param setArrayOfListsVar a 2D array of Strings to be set.
	*/
	
	public void setArrayOfLists(String [][] setArrayOfListsVar){
		arrayOfLists = setArrayOfListsVar;
	}
	
	/**
	Sets the 2D array of doubles to the provided value.
	@param setArrayOfLengthsVar a 2D array of doubles to be set.
	*/
	
	public void setArrayOfLengths (double [][] setArrayOfLengthsVar) {
		arrayOfLengths = setArrayOfLengthsVar;
	}

	/**
	 * getter method
	Returns the 2D array of Strings.
	@return a 2D array of Strings.
	*/
	
	public String [][] getArrayOfLists (){
		return arrayOfLists;
	}
	
	/**
	 * getter method
	Returns the 2D array of doubles.
	@return a 2D array of doubles.
	*/
	
	public double [][] getArrayOfLengths (){
		return arrayOfLengths;
	}
	//getarrayOfLists
	//getArrayOfLengths
}
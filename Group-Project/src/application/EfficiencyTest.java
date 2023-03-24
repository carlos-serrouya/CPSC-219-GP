package application;

import java.util.Arrays;

public class EfficiencyTest {

	public static String [][] setOfLists;
	public static double[][] setOfLengths;

	public static String[] best() {
	//Java doc this class and method will take the set of possible path combinations that follow our previos restrictions
	//and output the most effection path as a string array called bestPaths
	// Author: Carlos Serrouya

		String[] listOfPoints = {"A", "B", "C", "D"};
		int numOfPoints = listOfPoints.length;
		String[] maxPaths = {"AB","AC","AD","BC","BD","CD"};
		//will be used to define future variables
		
		double bestEfficiency = Double.MAX_VALUE;
		String[] bestPaths = {"there is no connected path that satisfies the input requirements"}; //will be displayed if no paths fit previous restricions
		int numOfLists = setOfLists.length;


		for (int q = 0; q < numOfLists; q++) {
			String[] paths = setOfLists[q];
			int numOfPaths = paths.length;
			double[] lengths = setOfLengths[q];
			double efficiency = Double.MAX_VALUE;
			efficiency = 0;
			for (int i1 = 0; i1 < maxPaths.length; i1++) {

				String getPoints = maxPaths[i1];
				char startPoint = getPoints.charAt(0);
				char endPoint = getPoints.charAt(1);
				boolean done = false;

				if (paths.length == 3) {
					for (int t = 0; t < 3; t++) {
						efficiency += 3*lengths[t];

					}	
					break;
					//basically I figured out that if theres n points and n-1 paths every path times n-1 is the efficiency
					//adding the previous code means all cases where you have to travel through all 4 pts on the shortest path
				}

				for (int t = 0; t < paths.length; t++) {

					String direct = "" + startPoint + endPoint; 


					if (paths[t].equals(direct)) {
						//if direct path from start pt to end pt it will always be shortest
						efficiency += lengths[t];
						done = true;
						break;
					}

				}
				//case 6 paths is accounted for

				int count = 0;
				String Astart = "" + "A" + startPoint;
				String startB = "" + startPoint + "B";
				String Bstart = "" + "B" + startPoint;
				String Cstart = "" + "C" + startPoint;
				String startC = "" + startPoint + "C";
				String startD = "" + startPoint + "D";
				//can never have A as endPoint
				if (!done) {
					for (int t = 0; t < paths.length; t++) {
						//how many times is the start point in paths
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

					char tstPtOne = startPoint;
					char tstPtTwo = startPoint;
					double tstOne = Double.MAX_VALUE;
					double tstTwo = Double.MAX_VALUE;

					for (int t = 0; t < paths.length; t++) {
						//only one of these should be true

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






}
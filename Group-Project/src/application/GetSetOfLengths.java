package application;

import java.util.Arrays;

public class GetSetOfLengths {

	
	public static double [] magnitudes;
	public static String [][] setOfLists;

	public static double[][]  filterlengths(){
	//java doc: this class/method will take the set of paths that fit previous restrictions and make
	// a 2d array using the corrosponding magnitudes. will return the 2d array setOfLengths
	// Author: Romeo Champagne

		double [][] setOfLengths = {};

		for (int t = 0; t < setOfLists.length; t++) {

			String[] tempSet = setOfLists[t];

			double[] tempLength = new double[tempSet.length];


			for (int q = 0; q < tempSet.length; q++) {

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
			}//matches corrosponding magniitudes to path adds to 1d array

			double[][] newsetOfLengths = Arrays.copyOf(setOfLengths, setOfLengths.length + 1);

			//deep copy
			newsetOfLengths[newsetOfLengths.length - 1] = tempLength;


			setOfLengths = newsetOfLengths;
			//adds 1 d array to 2 d array




		}

		return setOfLengths;


	}

}
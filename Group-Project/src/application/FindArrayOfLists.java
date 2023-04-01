package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindArrayOfLists {

	private static double[] magnitudes;
	private static double maxLength;


	private static String[][] filter() {
	//javadoc: tests to see if the different sets of paths in a powerset follows the
	//user input restrictions, and filters out some paths that arent connective
	// Author RomeChampagne
		
		String [] originalSetString = {"AB","AC","AD","BC","BD","CD"};

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

		double [][] setOfLengths = {};
		String [][] setOfLists = {};

		for (int t = 0; t < powerSetArray.length; t++) {

			String[] tempSet = powerSetArray[t];

			if (tempSet.length >= 3) {
				//if this isnt true the paths wont be path connected 


				double totalLength = 0;

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
	
	public static String [][] getFilter(){
		return FindArrayOfLists.filter();
	}
	
	public void setMagnitudes (double [] setMagnitudesVar){
		magnitudes = setMagnitudesVar;
	}
	
	public void setMaxLength (double setMaxLengthVar) {
		maxLength = setMaxLengthVar;
	}
	
	public double [] getMagnitudes () {
		return magnitudes;
	}
	
	public double getMaxLength () {
		return maxLength;
	}
}

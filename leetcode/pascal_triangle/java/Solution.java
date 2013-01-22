import java.util.ArrayList;


public class Solution {
	 public ArrayList<ArrayList<Integer>> generate(int numRows) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
		 	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		 	
		 	if (numRows <= 0) {
		 		return result;
		 	}
		 	
		 	for (int i = 1; i <= numRows; ++i) {
		 		int[] row = new int[i];
		 		ArrayList<Integer> rowList = new ArrayList<Integer>();
		 		row[0] = row[i-1] = 1;
		 		rowList.add(1);
		 		int l = 1;
		 		while (l < i-1) {
		 			row[l] = result.get(i-2).get(l) + result.get(i-2).get(l-1);
		 			rowList.add(row[l]);
		 			++l;
		 		}
		 		if (l < i) {
		 			rowList.add(1);
		 		}
		 		result.add(rowList);
		 	}
		 	
		 	return result;
	    }
}

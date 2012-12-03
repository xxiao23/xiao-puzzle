import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int canLength = candidates.length;
    	
    	// sort the candidates any way
        Arrays.sort(candidates);
        
        ArrayList<ArrayList<ArrayList<Integer>>> lastRow = new ArrayList<ArrayList<ArrayList<Integer>>>();
        ArrayList<ArrayList<ArrayList<Integer>>> curRow = new ArrayList<ArrayList<ArrayList<Integer>>>();
        
        int[][] t = new int[canLength][target+1];
        
        // initialize the 1st row
        // element = 1 if element mod candidate[0] is 0
        for (int j = 0; j < target+1; ++j) {
        	ArrayList<ArrayList<Integer>> oneSolution = new ArrayList<ArrayList<Integer>>();
        	if (j % candidates[0] == 0) {
        		t[0][j] = 1 ; 
        		ArrayList<Integer> oneSet = new ArrayList<Integer>();
        		for (int k = 0; k < j / candidates[0]; ++k) {
        			oneSet.add(candidates[0]);
        		}
        		oneSolution.add(oneSet);
        	} else {
        		t[0][j] = 0;
        	}
        	lastRow.add(oneSolution);
        }
        curRow.addAll(lastRow);
        
        // initialize the 1st column
        // all 1s
        for (int i = 0; i < canLength; ++i) {
        	t[i][0] = 1;
        }
        
        // calculate T matrix bottom up
        for (int i = 1; i < canLength; ++i) {
        	curRow.clear(); 
        	ArrayList<ArrayList<Integer>> emptySolution = new ArrayList<ArrayList<Integer>>();
        	emptySolution.add(new ArrayList<Integer>());
        	curRow.add(emptySolution); // the 1st one always empty for target = 0
        	for (int s = 1; s < target+1; ++s) {
        		ArrayList<ArrayList<Integer>> oneSolution = new ArrayList<ArrayList<Integer>>();
        		if (s - candidates[i] < 0) {
        			t[i][s] = t[i-1][s];
        			// same target with 1 less element
        			oneSolution.addAll(lastRow.get(s));
        		} else {
        			t[i][s] = t[i][s-candidates[i]] + t[i-1][s];
        			// same target with 1 less element
        			oneSolution.addAll(lastRow.get(s));
        			// candidates[i] union targt - candidates[i]
        			for(ArrayList<Integer> oneSet : curRow.get(s - candidates[i])) {
        				ArrayList<Integer> newSet = new ArrayList<Integer>();
        				newSet.addAll(oneSet);
        				newSet.add(candidates[i]);
        				oneSolution.add(newSet);
        			}
        		}
        		curRow.add(oneSolution);
        	}
    		lastRow.clear();
    		lastRow.addAll(curRow);
        }
        
        return curRow.get(target);
    }
}
import java.util.ArrayList;
import java.util.Arrays;


public class Solution1 {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int canLength = candidates.length;
    	
    	// sort the candidates any way
        Arrays.sort(candidates);
              
        int[][] t = new int[canLength][target+1];
        
        // initialize the 1st row
        // element = 1 if element mod candidate[0] is 0
        for (int j = 0; j < target+1; ++j) {
        	if (j % candidates[0] == 0) {
        		t[0][j] = 1 ; 
        	} else {
        		t[0][j] = 0;
        	}
        }
        
        // initialize the 1st column
        // all 1s
        for (int i = 0; i < canLength; ++i) {
        	t[i][0] = 1;
        }
        
        // calculate T matrix bottom up
        for (int i = 1; i < canLength; ++i) {
        	for (int s = 1; s < target+1; ++s) {
        		if (s - candidates[i] < 0) {
        			t[i][s] = t[i-1][s];
        		} else {
        			t[i][s] = t[i][s-candidates[i]] + t[i-1][s];
        		}
        	}

        }
        
        return constructSolutions(t, candidates, candidates.length-1, target);
    }
    
    private ArrayList<ArrayList<Integer>> constructSolutions(int[][] t, 
    		int[] candidates, int n, int target) {
//    	System.out.println("n = " + n + ", target = " + target);
    	
    	if (n == 0 || target == 0) {
    		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();	
    		if (target % candidates[n] == 0) {
    			ArrayList<Integer> oneSolution = new ArrayList<Integer>();
    			for(int i = 0; i < target / candidates[n]; ++i) {
    				oneSolution.add(candidates[n]);
    			}
    			result.add(oneSolution);
    		}	
    		return result;
    	} else if (target < 0 || n < 0 || t[n][target] == 0) {
    		return new ArrayList<ArrayList<Integer>>();
    	}
    	
    	ArrayList<ArrayList<Integer>> subSolution1 = 
    			constructSolutions(t, candidates, n, target-candidates[n]);
    	for(ArrayList<Integer> oneSolution : subSolution1) {
    		oneSolution.add(candidates[n]);
    	}
    	
    	ArrayList<ArrayList<Integer>> subSolution2 = 
    			constructSolutions(t, candidates, n-1, target);
    	subSolution1.addAll(subSolution2);

    	return subSolution1;
    }
}

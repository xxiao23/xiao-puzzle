import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(num);
        
        int numLength = num.length;

        @SuppressWarnings("unchecked")
		ArrayList<ArrayList<Integer>>[][] result = new ArrayList[2][target+1];
        int prevRow = 0;
        int curRow = 1;
        for (int s = 0; s < target+1; ++s) {
        	result[0][s] = new ArrayList<ArrayList<Integer>>();
        	result[1][s] = new ArrayList<ArrayList<Integer>>();
        }
        
        // initialization
        result[prevRow][0].add(new ArrayList<Integer>());
        result[curRow][0].add(new ArrayList<Integer>());
        
        // DP table
        for (int i = 0; i < numLength; ++i) {
        	result[curRow][0].clear();
        	result[curRow][0].add(new ArrayList<Integer>());
        	for (int s = 1; s < target+1; ++s) {
        		result[curRow][s].clear();
        		if (s - num[i] >= 0) {
        			for (ArrayList<Integer> oneSolution : result[prevRow][s-num[i]]) {
        				ArrayList<Integer> newSolution = new ArrayList<Integer>();
        				newSolution.addAll(oneSolution);
        				newSolution.add(num[i]);
        				result[curRow][s].add(newSolution);
        			}
        		}
        		result[curRow][s].addAll(result[prevRow][s]);
        	}
        	
        	// switch prev row and cur row pointer
        	int tmp = prevRow;
        	prevRow = curRow;
        	curRow = tmp;
        }
        
        Set<ArrayList<Integer>> solutionSet = new HashSet<ArrayList<Integer>>(result[curRow][target]);
        return new ArrayList<ArrayList<Integer>>(solutionSet);
    }
}
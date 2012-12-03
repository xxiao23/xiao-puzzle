
public class Solution {
    public int numDistinct(String S, String T) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	// DP table
    	int[][] result = new int[S.length()+1][T.length()+1];
    	
    	// 1st column to be 1s
    	for (int i = 0; i < S.length()+1; ++i) {
    		result[i][0] = 1;
    	}
    	
    	// filled the DP table bottom-up
    	for (int i = 1; i < S.length()+1; ++i) {
    		for (int j = 1; j < T.length()+1; ++j) {
    			result[i][j] = 0;
    			result[i][j] += result[i-1][j];
    			if (S.charAt(i-1) == T.charAt(j-1)) {
    				result[i][j] += result[i-1][j-1];
    			}
    		}
    	}
    	
    	return result[S.length()][T.length()];
    }
}
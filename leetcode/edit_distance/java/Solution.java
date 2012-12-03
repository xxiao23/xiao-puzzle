public class Solution {
    public int minDistance(String word1, String word2) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	int n = word1.length();
    	int m = word2.length();
    	
    	int[][] T = new int[n+1][m+1];
    	for (int j = 0; j < m+1; ++j) {
    		T[0][j] = j;
    	}
    	for (int i = 0; i < n+1; ++i) {
    		T[i][0] = i;
    	}
    	
    	for (int i = 1; i < n+1; ++i) {
    		for (int j = 1; j < m+1; ++j) {
    			if (word1.charAt(i-1) == word2.charAt(j-1)) {
    				T[i][j] = T[i-1][j-1];
    			} else {
    				T[i][j] = T[i][j-1] + 1;
    				if (T[i][j] > T[i-1][j]+1) {
    					T[i][j] = T[i-1][j]+1;
    				}
    				if (T[i][j] > T[i-1][j-1]+1) {
    					T[i][j] = T[i-1][j-1]+1;
    				}
    			}
    		}
    	}
    	return T[n][m];
    }
}

public class Solution {
    public int uniquePaths(int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (m <= 1 || n <= 1) {
        	return 1;
        }
        
        int[][] t = new int[2][n];
        int prev = 0, cur = 1;
        for (int j = 0; j < n; j++) {
        	t[prev][j] = 1;
        }
        
        for (int i = 1; i < m; ++i) {
        	t[cur][0] = 1;
        	for (int j = 1; j < n; ++j) {
        		t[cur][j] = t[cur][j-1] + t[prev][j];
        	}
        	int tmp = cur;
        	cur = prev;
        	prev = tmp; 
        }
        
        return t[prev][n-1];
    }
}

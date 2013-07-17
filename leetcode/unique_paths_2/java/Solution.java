
public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int m = obstacleGrid.length;
    	if (m == 0) {
    		return 1;
    	}
    	int n = obstacleGrid[0].length;
    	if (n == 0) {
        	return 1;
        }
        
        int[][] t = new int[2][n];
        int prev = 0, cur = 1;
        t[prev][0] = (obstacleGrid[0][0] == 0) ? 1 : 0;
        for (int j = 1; j < n; j++) {
        	t[prev][j] = (obstacleGrid[0][j] == 1) ? 0 : t[prev][j-1];
        }
        
        for (int i = 1; i < m; ++i) {
        	t[cur][0] = (obstacleGrid[i][0] == 1) ? 0 : t[prev][0];
        	for (int j = 1; j < n; ++j) {
        		t[cur][j] = 0;
        		if (obstacleGrid[i][j] == 1) {
        			continue;
        		}
        		t[cur][j] = t[cur][j-1] + t[prev][j];
        	}
        	int tmp = cur;
        	cur = prev;
        	prev = tmp; 
        }
        
        return t[prev][n-1];
    }
}

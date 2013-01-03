
public class Solution {
    public int minPathSum(int[][] grid) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
    	int nRows = grid.length;
    	if (nRows == 0) {
    		return 0;
    	}
    	
    	int nCols = grid[0].length;
    	int[][] t = new int[nRows][nCols];
    	
    	t[0][0] = grid[0][0];
    	for (int j = 1; j < nCols; ++j) {
    		t[0][j] = t[0][j-1] + grid[0][j];
    	}
    	
    	for (int i = 1; i < nRows; ++i) {
    		t[i][0] = t[i-1][0] + grid[i][0];
    	}
    	
    	for (int i = 1; i < nRows; ++i) {
    		for (int j = 1; j < nCols; ++j) {
    			t[i][j] = Math.min(t[i-1][j], t[i][j-1]) + grid[i][j];
    		}
    	}
    	
    	return t[nRows-1][nCols-1];
    	
    }
}

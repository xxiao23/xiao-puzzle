
public class Solution {
    public void rotate(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int row = matrix.length;
    	int col = row > 0 ? matrix[0].length : 0;
    	
    	// transpose the matrix
    	// i.e., mirror along the diagonal
    	for (int i = 0; i < row; ++i) {
    		for (int j = i+1; j < col; ++j) {
    			int tmp = matrix[j][i];
    			matrix[j][i] = matrix[i][j];
    			matrix[i][j] = tmp;
    		}
    	}
    	
    	// reverse each row
    	// i.e., mirror along the mid line
    	for (int i = 0; i < row; ++i) {
    		int l = 0;
    		int r = col-1;
    		while (l <= r) {
    			int tmp = matrix[i][l];
    			matrix[i][l] = matrix[i][r];
    			matrix[i][r] = tmp;
    			++l;
    			--r;
    		}
    	}
    }
}

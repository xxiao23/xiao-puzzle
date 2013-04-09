
public class Solution {
    public int[][] generateMatrix(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int[][] matrix = new int[n][n];
    	
    	int step = n;
    	int d = 0; // 0 - east, 1 - south, 2 - west, 3 - north
    	int i = 0, j = 0, e = 1;
    	while (step > 0) {
    		if (step == 1) {
    			matrix[i][j] = e;
    		}
    		
    		switch (d) {
    			case 0 :
    				for (int k = 0; k < step-1; k++) {
    					matrix[i][j++] = e++;
    				}
    				break;
    			case 1 :
    				for (int k = 0; k < step-1; k++) {
    					matrix[i++][j] = e++;
    				}
    				break;
    			case 2 :
    				for (int k = 0; k < step-1; k++) {
    					matrix[i][j--] = e++;
    				}
    				break;
    			case 3 :
    				for (int k = 0; k < step-1; k++) {
    					matrix[i--][j] = e++;
    				}
    				i++;
    				j++;
    				break;
    			default :
    				return matrix;
    		}
    		
    		d = (d+1) % 4;
    		if (d == 0) {
    			step -= 2;
    		}
    	}
    	
    	return matrix;
    }
}

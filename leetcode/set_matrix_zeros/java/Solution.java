import java.util.Random;


public class Solution {
    public void setZeroes(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	Random rand = new Random();
    	int marker = 0;
    	boolean markerExist = true;
    	while (markerExist) {
    		markerExist = false;
    		marker = rand.nextInt();
    		for (int i = 0; i < matrix.length; ++i) {
    			for (int j = 0; j < matrix[0].length; ++j) {
    				if (matrix[i][j] == marker) {
    					markerExist = true;
    					break;
    				}
    			}
    			if (markerExist) {
    				break;
    			}
    		}
    	}
    	    	
    	for (int i = 0; i < matrix.length; ++i) {
    		for (int j = 0; j < matrix[0].length; ++j) {
    			if (matrix[i][j] == 0) {
    				// change non-zero elements in this row to marker
    				for (int k = 0; k < matrix[0].length; ++k) {
    					if (matrix[i][k] != 0) {
    						matrix[i][k] = marker;
    					}
    				}
    				
    				// change non-zero elements in this column to marker
    				for (int l = 0; l < matrix.length; ++l) {
    					if (matrix[l][j] != 0) {
    						matrix[l][j] = marker;
    					}
    				}
    			}
    		}
    	}
    	
    	// change all marker to 0s
    	for (int i = 0; i < matrix.length; ++i) {
    		for (int j = 0; j < matrix[0].length; ++j) {
    			if (matrix[i][j] == marker) {
    				matrix[i][j] = 0;
    			}
    		}
    	}
    }
}

import java.util.ArrayList;


public class Solution {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	if (matrix.length == 0) {
    		return result;
    	}
    	
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int d = 0; // 0 - east; 1 - south; 2 - west; 3 - north
    	int i = 0;
    	int j = 0;
    	while (m > 0 && n > 0) {
    		if (m == 1) {
    			for (int k = 0; k < n; ++k, j++) {
    				result.add(matrix[i][j]);
    			}
    			break;
    		}
    		if (n == 1) {
    			for (int k = 0; k < m; ++k, i++) {
    				result.add(matrix[i][j]);
    			}
    			break;
    		}
    		// go down n elements in direction d
    		switch (d) {
    			case 0:
    				for (int k = 0; k < n-1; k++) {
    					result.add(matrix[i][j++]);
    				}
    				break;
    			case 1:
    				for (int k = 0; k < m-1; k++) {
    					result.add(matrix[i++][j]);
    				}
    				break;
    			case 2:
    				for (int k = 0; k < n-1; k++) {
    					result.add(matrix[i][j--]);
    				}
    				break;
    			case 3:
    				for (int k = 0; k < m-1; k++) {
    					result.add(matrix[i--][j]);
    				}
    				i++; j++; // reset the starting point
    				break;
    			default:
    				return null;
    		}
    		// update n & d
    		d = (d+1) % 4;
    		if (d == 0) {
    			m -= 2;
    			n -= 2;
    		}
    	}
    	return result;
    }
}

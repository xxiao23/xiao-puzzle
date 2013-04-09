import java.util.Arrays;


public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (matrix.length == 0) {
    		return false;
    	}
    	
    	// binary search within the first element of each row
    	int l = 0;
    	int r = matrix.length-1;
    	int mid;
    	while (l < r) {
    		mid = (l+r) / 2;
    		if (matrix[mid][0] == target) {
    			return true;
    		} else if (matrix[mid][0] < target) {
    			l = mid + 1;
    		} else {
    			r = mid - 1;
    		}
    	}
    	
        return Arrays.binarySearch(matrix[l], target) >= 0 ||
        	   ((l > 0) ? Arrays.binarySearch(matrix[l-1], target) >= 0 : false);
    }
}

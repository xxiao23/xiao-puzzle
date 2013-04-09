
public class Solution {
    public int searchInsert(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return 0;
    	}
    	
    	int l = 0;
    	int r = A.length-1;
    	int mid;
    	
    	while (l < r) {
    		mid = (l+r)/2;
    		if (A[mid] == target) {
    			return mid;
    		} else if (target < A[mid]) {
    			r = mid-1;
    		} else {
    			l = mid+1;
    		}
    	}
    	
    	// l == r
    	if (A[l] == target) {
    		return l;
    	} else if (target < A[l]) {
    		return l;
    	} else {
    		return l+1;
    	}
    	
    }
}

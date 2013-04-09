
public class Solution {
    public int search(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return -1;
    	}
    	
    	int l = 0;
    	int r = A.length-1;
    	int mid;
    	
    	while (l <= r) {
    		mid = (l+r) / 2;
    		if (A[mid] == target) {
    			return mid;
    		}
    		
    		// one of the two partitions
    		// must be sorted, i.e., not rotated
    		// 
    		// recall no duplicated elements
    		
    		if (A[mid] < A[r]) {
    			// right partition not rotated
    			// left partition might be rotated
    			if (A[mid] < target && target <= A[r]) {
    				l = mid+1;
    			} else {
    				r = mid-1;
    			}
    		} else {
    			// right partition is rotated
    			// then, left partition must not be rotated
    			if (A[l] <= target && target < A[mid]) {
    				r = mid-1;
    			} else {
    				l = mid+1;
    			}
    		}
    	}
    	return -1;
    }
}

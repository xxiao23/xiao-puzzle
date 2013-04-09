
public class Solution {
    public boolean search(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return false;
    	}
    	
    	int l = 0;
    	int r = A.length-1;
    	int mid = -1;
    	
    	while (l <= r) {
    		mid = (l+r) / 2;
    		if (A[mid] == target) {
    			return true;
    		}
    		
    		// at least one of the 2 partitions
    		// are not rotated
    		
    		if (A[mid] < A[r]) {
    			// right partition is not rotated
    			if (A[mid] < target && target <= A[r]) {
    				l = mid+1;
    			} else {
    				r = mid-1;
    			}
    		} else if (A[mid] > A[r]) {
    			// left partition is not rotated
    			if (A[l] <= target && target < A[mid]) {
    				r = mid-1;
    			} else {
    				l = mid+1;
    			}
    		} else {
    			// binary search won't do any better
    			// just linear scan
    			for (int i = l; i <=r; ++i) {
    				if (A[i] == target) {
    					return true;
    				}
    			}
    			break;
    		}
    	}
    	
    	return false;
    }
}

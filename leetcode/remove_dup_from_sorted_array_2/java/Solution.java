
public class Solution {
    public int removeDuplicates(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return 0;
    	}
    	
    	int count = 0;
    	int dupCount = 1;
    	int firstDup = 0;
    	for (int i = 1; i <= A.length; ++i) {
    		if (i < A.length && A[i] == A[i-1]) {
	    		if (dupCount < 2) {
	    			// at most 2 dups
	    			++dupCount;
	    		}
    		} else {
    			// copy last distinct elements in place
    			for (int k = 0; k < dupCount; ++k) {
    				A[count++] = A[firstDup+k];
    			}
    			firstDup = i;
    			dupCount = 1;
    		}
    	}
    	
    	return count;
    }
}

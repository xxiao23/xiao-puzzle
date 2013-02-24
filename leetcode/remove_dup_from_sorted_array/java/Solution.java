
public class Solution {
    public int removeDuplicates(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return 0;
    	}
    	int count = 1;
    	for (int i = 1; i < A.length; ++i) {
    		if (A[i] != A[i-1]) {
    			A[count++] = A[i];
    		}
    	}
    	
    	return count;
    }
}

public class Solution {
    public int firstMissingPositive(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return 1;
    	}
    	
    	int numOfNonPositiveIntegers = 0;
    	for (int i = 0; i < A.length; ++i) {
    		if (A[i] <= 0) {
    			numOfNonPositiveIntegers += 1;
    		}
    	}
    	
    	int i = 0;
    	while (i < A.length) {
    		if (A[i] <= 0 || A[i] == (i-numOfNonPositiveIntegers+1)
    				|| A[i] > A.length - numOfNonPositiveIntegers) {
    			// if A[i] is non positive
    			// or A[i] is at its correct position
    			// or A[i] is beyond the range of possible consecutive integers
    			// no need to deal with A[i]
    			++i;
    			continue;
    		}
    		
    		// A[i] > 0 and A[i] not in the right position
    		// swap A[i]
    		
    		int temp = A[i];
    		int pos = A[i] + numOfNonPositiveIntegers - 1;
    		if (A[i] == A[pos]) {
    			// if A[i] is the same as A[pos]
    			// skip to next iteration
    			// otherwise, it leads an inf loop
    			++i;
    			continue;
    		}
    		A[i] = A[pos];
    		A[pos] = temp;
    		
    	}
    	
    	for (i = numOfNonPositiveIntegers; i < A.length; ++i) {
    		if (A[i] != (i-numOfNonPositiveIntegers+1)) {
    			return (i-numOfNonPositiveIntegers+1);
    		}
    	}
    	
    	return A[A.length-1]+1;
    }
}
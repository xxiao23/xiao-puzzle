
public class Solution {
    public void sortColors(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
     
    	// essentially, this is 3-way partition
    	// use 1 as the pivot element
    	
    	int a = -1; // the last element being 0
    	int b = -1; // the last element begin 1
    	int i = 0;
    	for (; i < A.length; ++i) {
    		if (A[i] == 0) {
    			// move A[b+1] to A[i]
    			// move A[a+1] to A[b+1]
    			// move A[i] to A[a+1]
    			int tmp = A[i];
    			A[i] = A[b+1];
    			A[b+1] = A[a+1];
    			A[a+1] = tmp;
    			a++;
    			b++;
    		} else if (A[i] == 1) {
    			// swap A[b+1] and A[i]
    			int tmp = A[i];
    			A[i] = A[b+1];
    			A[b+1] = tmp;
    			b++;
    		} 
    		
    		// if A[i] == 2
    		// do nothing
    	}
    }
}

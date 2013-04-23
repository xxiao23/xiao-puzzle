public class Solution {
    // there is a better solution
    // which merge from the end of A towards its head
    // run-time is only O(m+n)
    public void merge(int A[], int m, int B[], int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	int i = 0, j = 0, k = 0;
    	
    	while (i < m && j < n) {
    		if (A[k] <= B[j]) {
    			++i;
    		} else {
    			// shift A[i..m] 1 place right
    			for (int ii = k+m-i-1; ii >= k; --ii) {
    				A[ii+1] = A[ii];
    			}
    			A[k] = B[j++];
    		}
    		++k;
    	}
    	
    	while (j < n) {
    		A[k++] = B[j++];
    	}
    }
}

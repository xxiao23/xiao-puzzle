import java.util.Arrays;


public class Solution {
    public int removeElement(int[] A, int elem) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	Arrays.sort(A);
    	
    	int i = 0;
    	for (; i < A.length && A[i] != elem; ++i) {}
    	int first = i;
    	for (; i < A.length && A[i] == elem; ++i) {}
    	int last = i-1;
    	
    	i = first;
    	for (int j = A.length-1; j >=0 && i <= last ; --j) {
    		A[i++] = A[j];
    	}
    	
    	return A.length - (last - first + 1);
    }
}

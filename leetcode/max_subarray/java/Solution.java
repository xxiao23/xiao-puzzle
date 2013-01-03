
public class Solution {
    public int maxSubArray(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (A.length == 0) {
        	return 0;
        }
        
    	int maxSum = Integer.MIN_VALUE;
    	int curSum = 0;
    	for (int i = 0; i < A.length; ++i) {
    		curSum += A[i];
    		maxSum = Math.max(curSum, maxSum);
    		if (curSum < 0) {
    			curSum = 0;
    		}
    	}
    	
    	return maxSum;
    }
}

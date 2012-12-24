public class Solution {
	public boolean canJump(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int end = 0;
        
        for (int i = 0; i < A.length-1; ++i) {
        	if (i <= end) {
        		end = Math.max(end, i+A[i]);
        	}
        }
        
        return (end >= A.length-1);
    }
}

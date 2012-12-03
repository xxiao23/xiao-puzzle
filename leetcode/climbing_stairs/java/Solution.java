public class Solution {
    public int climbStairs(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int f0 = 1;
    	int f1 = 1;
    	if (n == 0) {
    		return f0;
    	}
    	if (n == 1) {
    		return f1;
    	}
    	
    	int f2 = f0 + f1;

    	for (int i = 2; i <=n; i++) {
    		f2 = f1 + f0;
    		f0 = f1;
    		f1 = f2;
    	}
    	
    	return f2;
    }
}
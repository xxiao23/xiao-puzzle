
public class Solution {
    public int reverse(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (x >= 0) {
        	return reversePositive(x);
        } else {
        	return -1*reversePositive(-1*x);
        }
    }
    
    private int reversePositive(int x) {
    	int r = 0;
    	while (x > 0) {
    		r = 10*r + x % 10;
    		x /= 10;
    	}
    	
    	return r;
    }
}

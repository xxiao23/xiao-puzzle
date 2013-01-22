
public class Solution {
    public boolean isPalindrome(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int absx = Math.abs(x);
    	int xc = absx;
    	int r = 1;
    	while (xc >= 10) {
    		xc /= 10;
    		r *= 10;
    	}
    	
    	xc = absx;
    	while (xc > 0) {
    		int ld = xc % 10;
    		int rd = xc / r;
    		if (ld != rd) {
    			return false;
    		}
    		xc -= rd * r;
    		xc /= 10;
    		r /= 100;
    	}
    	
    	return true;
    }
}

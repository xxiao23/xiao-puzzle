public class Solution {
    public int[] plusOne(int[] digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (digits == null || digits.length == 0) {
    		return new int[]{1};
    	}
    	
    	int co = 1;
    	int tmp;
    	for (int i = digits.length-1; i >= 0; --i) {
    		tmp = digits[i] + co;
    		digits[i] = tmp % 10;
    		co = tmp / 10;
    	}
    	if (co == 0) {
    		return digits;
    	} else {
    		int[] nd = new int[digits.length+1];
    		nd[0] = co;
    		for (int i = 1; i<= digits.length; ++i) {
    			nd[i] = digits[i-1];
    		}
    		return nd;
    	}
    	
    }
}

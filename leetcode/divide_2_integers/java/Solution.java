public class Solution {
    public int divide(int dividend, int divisor) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	long remainder = dividend;
    	long absDivisor = divisor;
    	boolean isPositive = true;
    	if ((remainder < 0 && absDivisor > 0) || (remainder > 0 && absDivisor < 0)) {
    		isPositive  = false;
    	}
    	if (remainder < 0) {
    		remainder = -remainder;
    	}
    	if (absDivisor < 0) {
    		absDivisor = -absDivisor;
    	}
    	int quotient = 0;
    	
    	// binary long division
    	int numOfDividendBits = Long.toBinaryString(remainder).length();
    	int numOfDivisorBits = Long.toBinaryString(absDivisor).length();
    	absDivisor = absDivisor<<(numOfDividendBits - numOfDivisorBits);
    	for (int i = numOfDividendBits - numOfDivisorBits; i >= 0; --i) {
    		if (remainder >= absDivisor) {
    			quotient |= 1<<i;
    			remainder -= absDivisor;	
    		}		
    		absDivisor = absDivisor>>1;
    	}
    	
    	if (!isPositive) {
    		quotient *= -1;
    	}
    	
    	return quotient;
    }
}
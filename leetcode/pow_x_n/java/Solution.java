
public class Solution {
    public double pow(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (n >= 0) {
    		return positivePow(x, n);
    	} else {
    		return 1.0 / positivePow(x, -n);
    	}

    }
    
    private double positivePow(double x, int n) {
        if (n == 0) {
        	return 1;
        }
        
        double sqrt = positivePow(x, n/2);
        if (n % 2 == 0) {
        	return sqrt*sqrt;
        } else {
        	return sqrt*sqrt*x;
        }
    }
}

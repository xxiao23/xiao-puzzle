
public class Solution {
	
	private int count = 0;
    public int totalNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	count = 0;
    	if (n == 0) {
    		return count;
    	}
    	
    	int[] cb = new int[n];
    	for (int i = 0; i < n; ++i) {
    		cb[i] = -1;
    	}
    	totalNQueensRecursive(cb, n, 0);
    	return count;
    }
    
    private void totalNQueensRecursive(int[] cb, int n, int k) {
    	if (k == n) {
    		count += 1;
    		return;
    	}
    	
    	for (int f = 0; f < n; ++f) {
    		if (acceptNewQueen(cb, n, k, f)) {
    			cb[k] = f;
    			totalNQueensRecursive(cb, n, k+1);
    			cb[k] = -1;
    		}
    	}
    }
    
    private boolean acceptNewQueen(int[] cb, int n, int k, int f) {
    	for (int r = 0; r < k; ++r) {
    		if (cb[r] == f) {
    			return false;
    		}
    		if (cb[r] + r == f + k || cb[r] - r == f - k) {
    			return false;
    		}
    	}
    	return true;
    }

}

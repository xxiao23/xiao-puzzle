import java.util.ArrayList;

/**
 * fastest and simplest solution so far
 * 
 * @author xiang
 *
 */
public class Solution3 {
	private ArrayList<String[]> result = new ArrayList<String[]>();

    public ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	result.clear();
    	if (n == 0) {
    		return result;
    	}
    	
    	int[] cb = new int[n];
    	totalNQueensRecursive(cb, n, 0);
    	return result;
    }
    
    private void totalNQueensRecursive(int[] cb, int n, int k) {
    	if (k == n) {
        	// construct the solution
    		char[][] ca = new char[n][n];
    		for (int i = 0; i < n; ++i) {
    			for (int j = 0; j < n; ++j) {
    				ca[i][j] = '.';
    			}
    		}
        	for (int r = 0; r < n; ++r) {
        		ca[r][cb[r]] = 'Q';
        	}
        	String[] caStr = new String[n];
        	for (int i = 0; i < n; ++i) {
        		caStr[i] = new String(ca[i]);
        	}
        	result.add(caStr);

    		return;
    	}
    	
    	for (int f = 0; f < n; ++f) {
    		if (acceptNewQueen(cb, n, k, f)) {
    			cb[k] = f;
    			totalNQueensRecursive(cb, n, k+1);
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

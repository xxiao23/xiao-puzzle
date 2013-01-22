import java.util.ArrayList;

/*
 * this recursive solution passed both small and large test case.
 */
public class Solution2 {
	static ArrayList<String[]> result = new ArrayList<String[]>();
	
    public ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
    	// DO NOT write main() function
    	result.clear();
    	if (n == 0) {
    		return result;
    	}
    	short[][] cb = new short[n][n];
    	solveNQueenRecursive(n, n, cb, 0);
    	return result;
    }
    
    private void solveNQueenRecursive(int n, int k, short[][]cb, int sr) {
    	if (k == 0) {
    		// output the configuration
    		char[][] ca = new char[n][n];
    		for (int r = 0; r < n; ++r) {
    			for (int f = 0; f < n; ++f) {
    				if (cb[r][f] == 1) {
    					ca[r][f] = 'Q';
    				} else {
    					ca[r][f] = '.';
    				}
    			}
    		}

    		String[] cbStr = new String[n];
    		for (int i = 0; i < n; ++i) {
    			cbStr[i] = new String(ca[i]);
    		}
    		result.add(cbStr);
    		return;
    	}
    	
    	for (int r = sr; r < n; ++r) {
    		for (int f = 0; f < n; ++f) {
    			if (cb[r][f] == 0) {
    				cb[r][f] = 1;
    				if (isNewQueenAccepted(r, f, cb, n)) {
    					solveNQueenRecursive(n, k-1, cb, r+1);
    				}
    				cb[r][f] = 0;
    			}
    		}
    	}
    }
    
    private boolean isNewQueenAccepted(int r, int f, short[][] cb, int n) {
    	// check the same row
    	for (int ff = 0; ff < n; ++ff) {
    		if (ff != f && cb[r][ff] == 1) {
    			return false;
    		}
    	}
    	// check the same column
    	for (int rr = 0; rr < n; ++rr) {
    		if (rr != r && cb[rr][f] == 1) {
    			return false;
    		}
    	}
    	// check the 2 diagonals
		int sum = r + f;
		for (int rr = 0; rr < n; ++rr) {
			if (sum-rr < n && sum-rr >= 0 && rr != r && cb[rr][sum-rr] == 1) {
				return false;
			}
		}

		int diff = r - f;
		for (int rr = 0; rr < n; ++rr) {
			if (rr-diff < n && rr-diff >= 0 && rr != r && cb[rr][rr-diff] ==1) {
				return false;
			}
		}
		
		return true;
    }
}

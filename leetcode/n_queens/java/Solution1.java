import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;

/*
 * bottom up DP-like
 * failed small test case due to not enough memory
 */
public class Solution1 {
	public ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<String[]> result = new ArrayList<String[]>();

    	if (n == 0) {
    		return result;
    	}
    	
    	LinkedList<ArrayList<BitSet[]>> t = 
    			new LinkedList<ArrayList<BitSet[]>>();
    	LinkedList<ArrayList<BitSet[]>> accept =
    			new LinkedList<ArrayList<BitSet[]>>();
    	
    	// base case when n = 1;
    	ArrayList<BitSet[]> ss = 
    			new ArrayList<BitSet[]>();
    	ArrayList<BitSet[]> as = 
    			new ArrayList<BitSet[]>();
    	for (int r = 0; r < n; ++r) {
    		for (int f = 0; f < n; ++f) {
    			BitSet[] config = new BitSet[n];
    			BitSet[] aConfig = new BitSet[n];
    			for (int i = 0; i < n; ++i) {
    				config[i] = new BitSet(n);
    				config[i].clear();
    				aConfig[i] = new BitSet(n);
    				aConfig[i].clear();
    			}
    			// create accept map
    			markAcceptSquare(aConfig, config, r, f, n);
    			as.add(aConfig);
    			ss.add(config);
    		}
    	}
    	t.add(ss);
    	accept.add(as);
    	
    	int cn = 2;
    	while (cn <= n) {
    		// larger subproblem solution
    		ArrayList<BitSet[]> ps = t.poll();
    		ArrayList<BitSet[]> aps = accept.poll();
    		ArrayList<BitSet[]> ls = new ArrayList<BitSet[]>();
    		ArrayList<BitSet[]> als = new ArrayList<BitSet[]>();
    		for (int i = 0; i < ps.size(); ++i) {
    			BitSet[] config = ps.get(i);
    			BitSet[] aConfig = aps.get(i);
    	    	int r = n-1;
    	    	while (r >= 0 && config[r].isEmpty()) {
    	    		--r;
    	    	}
    	    	for (r = r+1; r < n; ++r) {
    	    		for (int f = 0; f < n; ++f) {
    	    			if (aConfig[r].get(f) == false) {
    	    				BitSet[] newAConfig = new BitSet[n];
    	    				BitSet[] newConfig = new BitSet[n];
    	    				for (int ii = 0; ii < n; ++ii) {
    	    					newAConfig[ii] = new BitSet(n);
    	    					newAConfig[ii].or(aConfig[ii]);
    	    					newConfig[ii] = new BitSet(n);
    	    					newConfig[ii].or(config[ii]);
    	    				}
    	    				markAcceptSquare(newAConfig, newConfig, r, f, n);
    	    				ls.add(newConfig);
    	    				als.add(newAConfig);
    	    			}
    	    		}
    	    	}
    		}
    		t.add(ls);
    		accept.add(als);
    		
    		// next sub-problem
    		++cn;
    	}
    	
    	// construct the solution
    	for (BitSet[] config : t.poll()) {
    		char[][] cb = new char[n][n];
    		for (int r = 0; r < n; ++r) {
    			for (int f = 0; f < n; ++f) {
    				if (config[r].get(f)) {
    					cb[r][f] = 'Q';
    				} else {
    					cb[r][f] = '.';
    				}
    			}
    		}

    		String[] cbStr = new String[n];
    		for (int i = 0; i < n; ++i) {
    			cbStr[i] = new String(cb[i]);
    		}
    		result.add(cbStr);
    	}
    	return result;
    }
        
    private void markAcceptSquare(BitSet[] aConfig, BitSet[] config, int r, int f, int n) {
    	
    	config[r].set(f); // place the new queen
    	
    	// mark the row
    	for (int ff = 0; ff < n; ++ff) {
    		aConfig[r].set(ff);
    	}
    	// mark the column
    	for (int rr = 0; rr < n; ++rr) {
    		aConfig[rr].set(f);
    	}
    	// mark the 2 diagonals
		int sum = r + f;
		for (int rr = 0; rr < n; ++rr) {
			if (sum-rr < n && sum-rr >= 0) {
				aConfig[rr].set(sum-rr);
			}
		}

		int diff = r - f;
		for (int rr = 0; rr < n; ++rr) {
			if (rr-diff < n && rr-diff >= 0) {
				aConfig[rr].set(rr-diff);
			}
		}
    }
    

}

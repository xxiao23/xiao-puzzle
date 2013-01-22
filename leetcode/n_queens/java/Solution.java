import java.util.ArrayList;

/*
 * bottom up DP-like
 * passed small case
 * failed large case due to time limit
 */
public class Solution {
	 
    public ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<String[]> result = new ArrayList<String[]>();

    	if (n == 0) {
    		return result;
    	}
    	
    	ArrayList<ArrayList<ArrayList<ChessSquare>>> t = 
    			new ArrayList<ArrayList<ArrayList<ChessSquare>>>();
    	
    	// base case when n = 1;
    	ArrayList<ArrayList<ChessSquare>> ss = 
    			new ArrayList<ArrayList<ChessSquare>>();
    	for (int r = 0; r < n; ++r) {
    		for (int f = 0; f < n; ++f) {
    			ArrayList<ChessSquare> os = 
    					new ArrayList<ChessSquare>();
    			os.add(new ChessSquare(r, f));
    			ss.add(os);
    		}
    	}
    	t.add(ss);
    	
    	int cn = 2;
    	while (cn <= n) {
    		// larger subproblem solution
    		ArrayList<ArrayList<ChessSquare>> ls =
    				new ArrayList<ArrayList<ChessSquare>>();
    		for (ArrayList<ChessSquare> config : t.get(cn-2)) {
    	    	int[][] cbInt = new int[n][n];
    	    	markAcceptSquare(config, cbInt, n);
    	    	
    	    	// queen with the highest rank
    			ChessSquare hq = config.get(config.size()-1);
    			for (int r = hq.rank+1; r < n; ++r) {
    				for (int f = 0; f < n; ++f) {
    					// now each possible placement
    					// of the extra queen in lower ranks		
    					if (cbInt[r][f] == 0) {
    						ChessSquare nq = new ChessSquare(r, f);
    						ArrayList<ChessSquare> newConfig = 
    								new ArrayList<ChessSquare>();
    						newConfig.addAll(config);
    						newConfig.add(nq);
    						ls.add(newConfig);
    					}
    				}
    			}
    		}
    		t.add(ls);
    		
    		// next sub-problem
    		++cn;
    	}
    	
    	// construct the solution
    	for (ArrayList<ChessSquare> config : t.get(n-1)) {
    		char[][] cb = new char[n][n];
    		for (int i = 0; i < n; ++i) {
    			for (int j = 0; j < n; ++j) {
    				cb[i][j] = '.';
    			}
    		}
    		for (ChessSquare cs : config) {
    			cb[cs.rank][cs.file] = 'Q';
    		}
    		String[] cbStr = new String[n];
    		for (int i = 0; i < n; ++i) {
    			cbStr[i] = new String(cb[i]);
    		}
    		result.add(cbStr);
    	}
    	return result;
    }
        
    private void markAcceptSquare(ArrayList<ChessSquare> config, int[][] cbInt, int n) {
    	for (ChessSquare cs : config) {
    		// mark the same rank as 1
    		for (int j = 1; j < n; ++j) {
    			cbInt[cs.rank][j] = 1;
    		}
    		
    		// mark the same file as 1
    		for (int i = 0; i < n; ++i) {
    			cbInt[i][cs.file] = 1;
    		}
    		
    		// mark the 2 diagonals as 1
    		int sum = cs.rank + cs.file;
    		for (int i = 0; i < n; ++i) {
    			if (sum-i < n && sum-i >= 0) {
    				cbInt[i][sum-i] = 1;
    			}
    		}

    		int diff = cs.rank - cs.file;
    		for (int i = 0; i < n; ++i) {
    			if (i-diff < n && i-diff >= 0) {
    				cbInt[i][i-diff] = 1;
    			}
    		}
    	}
    }
    
    private static class ChessSquare {
    	// top left is 0,0
    	// bottom right is n-1,n-1
    	public int rank; // row
    	public int file; // column
    	
    	public ChessSquare(int r, int f) {
    		rank = r;
    		file = f;
    	}

		@Override
		public String toString() {
			return "(" + rank + "," + file + ")";
		}
    	
    }
}

import java.util.BitSet;


public class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
    	if (board == null) {
    		return true;
    	}
    	
    	BitSet check = new BitSet(9);
    	
    	// check each row
    	for (int i = 0; i < 9; i++) {
    		check.clear();
    		for (int j = 0; j < 9; j++) {
    			if (board[i][j] != '.') {
    				if (check.get(board[i][j]-'1')) {
    					return false;
    				} else {
    	    			check.set(board[i][j]-'1');
    				}
    			}
    		}
    	}
    	
    	// check each column
    	for (int j = 0; j < 9; j++) {
    		check.clear();
    		for (int i = 0; i < 9; ++i) {
    			if (board[i][j] != '.') { 
    				if (check.get(board[i][j]-'1')) {
    					return false;
    				} else {
    	    			check.set(board[i][j]-'1');
    				}
    			}
    		}
    	}
    	
    	// check each sub-block
    	for (int k = 0; k < 9; k++) {
    		check.clear();
    		int i = (k / 3) * 3;
    		int j = (k % 3) * 3;
    		for (int ii = i; ii < i+3; ii++) {
    			for (int jj = j; jj < j+3; jj++) {
    				if (board[ii][jj] != '.') { 
    					if (check.get(board[ii][jj]-'1')) {
    						return false;
    					} else {
    	    				check.set(board[ii][jj]-'1');
    					}
    				}
    			}
    		}
    	}
    	
    	return true;
    }
}

import java.util.BitSet;


public class Solution {
	BitSet[] rows = null;
	BitSet[] cols = null;
	BitSet[] tiles = null;
	
    public void solveSudoku(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (board.length != 9) {
    		return;
    	}
    	
    	if (board[0].length != 9) {
    		return;
    	}
    	
    	rows = new BitSet[9];
    	cols = new BitSet[9];
    	tiles = new BitSet[9];
    	for (int i = 0; i < 9; ++i) {
    		rows[i] = new BitSet(9);
    		cols[i] = new BitSet(9);
    		tiles[i] = new BitSet(9);
    	}
    	
    	// initialize the board
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			if (board[i][j] != '.') {
    				int s = board[i][j] - '1';
    				rows[i].set(s);
    				cols[j].set(s);
    				tiles[i/3*3+j/3].set(s);
    			}
    		}
    	}
    	
    	solveSudokuRecursive(board, 0, 0);
    }
    
    /*
     * start from board[i][j],
     * search for the first empty spot
     * and try fill that empty spot with viable numbers.
     * then solve the rest of board recursively
     */
    private boolean solveSudokuRecursive(char[][] board, int i, int j) {
    	// search for the first empty spot
    	int k = i, t = j;
    	while (k < 9 && board[k][t] != '.') {
			t = (t+1) % 9;
			if (t == 0) {
				k++;
			}
    	}
    	
    	// all spots filled
    	if (k == 9) {
    		return true;
    	}
    	
    	// board[k][t] is the empty spot
    	// we need to try to fill
    	for (int s = 0; s < 9; ++s) {
    		if (rows[k].get(s) == false && cols[t].get(s) == false && tiles[k/3*3+t/3].get(s) == false) {
    			rows[k].set(s);
    			cols[t].set(s);
    			tiles[k/3*3+t/3].set(s);
    			board[k][t] = (char) ('1' + s);
    			int nextT = (t+1) % 9;
    			int nextK = k;
    			if (nextT == 0) {
    				nextK += 1;
    			}
    			if (solveSudokuRecursive(board, nextK, nextT)) {
    				return true;
    			} 
    			rows[k].clear(s);
    			cols[t].clear(s);
    			tiles[k/3*3+t/3].clear(s);
    			board[k][t] = '.';
    		}
    	}
    	return false;
    }
}

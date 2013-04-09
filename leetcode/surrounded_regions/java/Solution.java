import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Solution {
	private BitSet[] vs;
	
	public void solve(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (board.length == 0) {
			return;
		}
		
		vs = new BitSet[board.length];
		for (int i = 0; i < vs.length; ++i) {
			vs[i] = new BitSet(board[0].length);
		}
        
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length; ++j) {
				if (vs[i].get(j) == false && board[i][j] == 'O') {
					bfs(new Node(i, j), board);
				}
			}
		}
    }
	
	private void bfs(Node s, char[][] board) {
		Queue<Node> q = new LinkedList<Node>();
		Set<Node> bfsSet = new HashSet<Node>();
		q.add(s);
		vs[s.row].set(s.col);
		boolean setX = true;
		while (q.size() > 0) {
			Node h = q.poll();
			bfsSet.add(h);
			if (h.row == 0 || h.row == board.length-1 || h.col == 0 || h.col == board[0].length-1) {
				// if any 'O' is on the boundary
				// the set of 'O's cannot be surrounded by 'X's 
				setX = false;
			}
			if (h.col > 0 && vs[h.row].get(h.col-1) == false && board[h.row][h.col-1] == 'O') {
				// check if left is O
				q.add(new Node(h.row, h.col-1));
				vs[h.row].set(h.col-1);
			}
			if (h.row > 0 && vs[h.row-1].get(h.col) == false && board[h.row-1][h.col] == 'O') {
				// check if up is O
				q.add(new Node(h.row-1, h.col));
				vs[h.row-1].set(h.col);
			}
			if (h.col < board[0].length-1 && vs[h.row].get(h.col+1) == false && board[h.row][h.col+1] == 'O') {
				// check if right is O
				q.add(new Node(h.row, h.col+1));
				vs[h.row].set(h.col+1);
			}
			if (h.row < board.length-1 && vs[h.row+1].get(h.col) == false && board[h.row+1][h.col] == 'O') {
				// check if lower is O
				q.add(new Node(h.row+1, h.col));
				vs[h.row+1].set(h.col);
			}
		}
		
		if (setX) {
			for (Node n : bfsSet) {
				board[n.row][n.col] = 'X';
			}
		}
	}
	
	private static class Node {
		int row;
		int col;
		public Node(int i, int j) {
			row = i;
			col = j;
		}
		
		@Override
		public String toString() {
			return "Node [row=" + row + ", col=" + col + "]";
		}
	}
}

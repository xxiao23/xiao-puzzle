
import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		char[][] board = { {'X', 'X', 'X', 'X'},
						   {'X', 'O', 'O', 'X'},
						   {'X', 'X', 'O', 'X'},
						   {'X', 'O', 'X', 'X'} };
		s.solve(board);
		for (int i = 0; i < board.length; ++i) {
			System.out.println(Arrays.toString(board[i]));
		}
	}

	@Test
	public void test2() {
		char[][] board = { {'X', 'X', 'X', 'X'},
						   {'X', 'O', 'O', 'X'},
						   {'X', 'X', 'O', 'O'},
						   {'X', 'O', 'X', 'X'} };
		s.solve(board);
		for (int i = 0; i < board.length; ++i) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
	
	@Test
	public void test3() {
		char[][] board = { {'X', 'X', 'X', 'X'},
						   {'X', 'O', 'X', 'X'},
						   {'X', 'X', 'X', 'O'},
						   {'X', 'O', 'X', 'X'} };
		s.solve(board);
		for (int i = 0; i < board.length; ++i) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
	
	@Test
	public void test4() {
		char[][] board = { {'O', 'O'},
						   {'O', 'O'} };
		s.solve(board);
		for (int i = 0; i < board.length; ++i) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
}

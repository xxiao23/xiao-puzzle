import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int result = s.totalNQueens(0);
		assertTrue("result = " + result, result == 0);
	}
	
	@Test
	public void test2() {
		int result = s.totalNQueens(1);
		assertTrue("result = " + result, result == 1);
	}

	@Test
	public void test3() {
		int result = s.totalNQueens(4);
		assertTrue("result = " + result, result == 2);
	}
	
	@Test
	public void test4() {
		int result = s.totalNQueens(9);
		assertTrue("result = " + result, result == 352);
	}
	
	@Test
	public void test5() {
		int result = s.totalNQueens(11);
		assertTrue("result = " + result, result == 2680);
	}
}

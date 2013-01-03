import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest extends Solution {
	Solution s = new Solution();
	
	@Test
	public void testMinPathSum1() {
		int[][] grid = {};
		int result = s.minPathSum(grid);
		assertTrue("result = " + result, result == 0);
	}
	
	@Test
	public void testMinPathSum2() {
		int[][] grid = {{1, 2, 3}};
		int result = s.minPathSum(grid);
		assertTrue("result = " + result, result == 6);
	}
	
	@Test
	public void testMinPathSum3() {
		int[][] grid = {{1,2}, {1, 1}};
		int result = s.minPathSum(grid);
		assertTrue("result = " + result, result == 3);
	}

}

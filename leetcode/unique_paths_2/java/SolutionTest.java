import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[][] m = { {0, 0}, {0, 0} };
		int result = s.uniquePathsWithObstacles(m);
		assertTrue(""+result, result == 2);
	}
	
	@Test
	public void test2() {
		int[][] m = {{0, 0, 0}};
		int result = s.uniquePathsWithObstacles(m);
		assertTrue(""+result, result == 1);
	}
	
	@Test
	public void test3() {
		int[][] m = { { 0, 1}, {0 , 0} };
		int result = s.uniquePathsWithObstacles(m);
		assertTrue(""+result, result == 1);
	}
	
	@Test
	public void test4() {
		int[][] m = { {0, 1}, {1, 0} };
		int result = s.uniquePathsWithObstacles(m);
		assertTrue(""+result, result == 0);
	}
	
	@Test
	public void test5() {
		int[][] m = { {1} };
		int result = s.uniquePathsWithObstacles(m);
		assertTrue(""+result, result == 0);
		
		int[][] m2 = { {0, 1} };
		result = s.uniquePathsWithObstacles(m2);
		assertTrue(""+result, result == 0);
	}
	
	@Test
	public void test6() {
		int[][] m = { {0}, {1} };
		int result = s.uniquePathsWithObstacles(m);
		assertTrue(""+result, result == 0);
	}
}

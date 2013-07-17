import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int result = s.uniquePaths(1, 1);
		assertTrue(""+result, result == 1);
		result = s.uniquePaths(1, 10);
		assertTrue(""+result, result == 1);
		result = s.uniquePaths(10, 1);
		assertTrue(""+result, result == 1);
	}
	
	@Test
	public void test2() {
		int result = s.uniquePaths(2, 2);
		assertTrue(""+result, result == 2);
	}

}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMaximalRectangle1() {
		char[][] a = {};
		int result = s.maximalRectangle(a);
		assertTrue(result == 0);
	}

	@Test
	public void testMaximalRectangle2() {
		char[][] a = {{'0'}};	
		int result = s.maximalRectangle(a);
		assertTrue(result == 0);
	}
	
	@Test
	public void testMaximalRectangle3() {
		char[][] a = {{'1'}};	
		int result = s.maximalRectangle(a);
		assertTrue(result == 1);
	}
	
	@Test
	public void testMaximalRectangle4() {
		char[][] a = {{'0', '0'}};	
		int result = s.maximalRectangle(a);
		assertTrue(result == 0);
	}
	
	@Test
	public void testMaximalRectangle5() {
		char[][] a = {{'1', '1'}};	
		int result = s.maximalRectangle(a);
		assertTrue(result == 2);
	}
	
}

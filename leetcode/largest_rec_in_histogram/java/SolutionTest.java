import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLargestRectangleArea1() {
		int[] h = {2,1,5,6,2,3};
		int result = s.largestRectangleArea(h);
		assertTrue(result == 10);
	}
	
	@Test
	public void testLargestRectangleArea2() {
		int[] h = {3,6,5,7,4,8,1,0};
		int result = s.largestRectangleArea(h);
		assertTrue(result == 20);
	}

}

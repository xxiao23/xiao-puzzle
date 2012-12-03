import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testMaxArea() {
		int[] height = {1,2,1};
		Solution solution = new Solution();
		int maxArea = solution.maxArea(height);
		System.out.println(maxArea);
		assertTrue(maxArea == 2);
	}
	
	@Test
	public void testMaxArea1() {
		int[] height = {1,2,3,4,5,6,7,8,9,10};
		Solution solution = new Solution();
		int maxArea = solution.maxArea(height);
		System.out.println(maxArea);
		assertTrue(maxArea == 25);
	}

}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();

	@Test
	public void testMaxProfit1() {
		int[] p = {};
		int result = s.maxProfit(p);
		System.out.println("result = " + result);
		assertTrue(result == 0);
	}

	@Test
	public void testMaxProfit2() {
		int[] p = {1,4,2};
		int result = s.maxProfit(p);
		System.out.println("result = " + result);
		assertTrue(result == 3);
	}
	
	@Test
	public void testMaxProfit3() {
		int[] p = {1,2,4,2,5,7,2,4,9,0,9};
		int result = s.maxProfit(p);
		System.out.println("result = " + result);
		assertTrue(result == 17);
	}
	
	@Test
	public void testMaxProfit4() {
		int[] p = {2,1};
		int result = s.maxProfit(p);
		System.out.println("result = " + result);
		assertTrue(result == 0);
	}
	
	@Test
	public void testMaxProfit5() {
		int[] p = {6,1,3,2,4,7};
		int result = s.maxProfit(p);
		System.out.println("result = " + result);
		assertTrue(result == 7);
	}
}

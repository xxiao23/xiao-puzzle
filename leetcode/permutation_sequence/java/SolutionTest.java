import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test	
	public void testGetPermutation1() {
		int n = 3;
		int k = 3;
		String result = s.getPermutation(n, k);
		assertTrue("result = " + result, "213".equals(result));
	}
	
	@Test
	public void testGetPermutation2() {
		int n = 9;
		int k = 360000;
		String result = s.getPermutation(n, k);
		System.out.println(result);
	}
	
	@Test
	public void testGetPermutation3() {
		int n = 9;
		int k = 1;
		String result = s.getPermutation(n, k);
		assertTrue("result = " + result, "123456789".equals(result));
	}
}

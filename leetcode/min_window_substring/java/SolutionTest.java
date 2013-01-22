import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMinWindow1() {
		String S = "ADOBECODEBANC";
		String T = "ABC";
		
		String result = s.minWindow(S, T);
		System.out.println(result);
		assertTrue("BANC".equals(result));
	}
	
	@Test
	public void testMinWindow2() {
		String S = "ADOBECODEBANC";
		String T = "AABC";
		
		String result = s.minWindow(S, T);
		System.out.println(result);
		assertTrue("ADOBECODEBA".equals(result));
	}
	
	@Test
	public void testMinWindow3() {
		String S = "a";
		String T = "aa";
		
		String result = s.minWindow(S, T);
		System.out.println(result);
		assertTrue("".equals(result));
	}

}

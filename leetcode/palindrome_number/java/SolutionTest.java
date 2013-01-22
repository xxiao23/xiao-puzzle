import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testIsPalindrome1() {
		int n = 1221;
		boolean result = s.isPalindrome(n);
		assertTrue(result);
		
		n = -1221;
		result = s.isPalindrome(n);
		assertTrue(result);
		
		n = 1231;
		result = s.isPalindrome(n);
		assertFalse(result);
	}
	
	@Test
	public void testIsPalindrome2() {
		int n = -2147483648;
		boolean result = s.isPalindrome(n);
		assertFalse(result);
	}
	
	@Test
	public void testIsPalindrome3() {
		int n = 2147447412;
		boolean result = s.isPalindrome(n);
		assertTrue(result);
	}
}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testIsPalindrome1() {
		String str = "a";
		boolean result = s.isPalindrome(str);
		assertTrue(result);
	}

	@Test
	public void testIsPalindrome2() {
		String str = "A man, a plan, a canal: Panama";
		boolean result = s.isPalindrome(str);
		assertTrue(result);
	}
	
	@Test
	public void testIsPalindrome3() {
		String str = "race a car";
		boolean result = s.isPalindrome(str);
		assertFalse(result);
	}
	
	@Test
	public void testIsPalindrome4() {
		String str = " ";
		boolean result = s.isPalindrome(str);
		assertTrue(result);
	}
	
	@Test
	public void testIsPalindrome5() {
		String str = "a.";
		boolean result = s.isPalindrome(str);
		assertTrue(result);
	}	
	
	@Test
	public void testIsPalindrome6() {
		String str = ".";
		boolean result = s.isPalindrome(str);
		assertTrue(result);
	}
	
}

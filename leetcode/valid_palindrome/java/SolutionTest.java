import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	@Test
	public void test() {
		assertTrue(s.isPalindrome(""));
		assertTrue(s.isPalindrome("A man, a plan, a canal: Panama"));
		assertFalse(s.isPalindrome("race a car"));
		assertTrue(s.isPalindrome(" "));
	}

}

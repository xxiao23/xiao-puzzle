import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLongestPalindrome() {
		String a = "a";
		String result = s.longestPalindrome(a);
		assertTrue("a".equals(result));
		
		String b = "bb";
		result = s.longestPalindrome(b);
		assertTrue("bb".equals(result));
		
		String c = "adam";
		result = s.longestPalindrome(c);
		assertTrue("ada".equals(result));
	}

}

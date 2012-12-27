import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLongestValidParentheses() {
		String a = "(()";
		int result = s.longestValidParentheses(a);
		assertTrue(result == 2);
		
		String b = ")()())";
		result = s.longestValidParentheses(b);
		assertTrue(result == 4);
		
		String c = "()(()";
		result = s.longestValidParentheses(c);
		assertTrue(result == 2);
	}

}

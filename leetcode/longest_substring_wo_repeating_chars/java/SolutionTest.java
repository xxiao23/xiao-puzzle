import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLengthOfLongestSubstring() {
		String a = "abcabcbb";
		int result = s.lengthOfLongestSubstring(a);
		assertTrue(result == 3);
		
		String b = "bbbbb";
		result = s.lengthOfLongestSubstring(b);
		assertTrue(result == 1);
		
		String c = "abcd";
		result = s.lengthOfLongestSubstring(c);
		assertTrue(result == 4);
	}

}

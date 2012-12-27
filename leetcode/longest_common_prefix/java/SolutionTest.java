import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLongestCommonPrefix1() {
		String[] strs = {"aa", "ab"};
		String result = s.longestCommonPrefix(strs);
		System.out.println(result);
		assertTrue("a".equals(result));
	}
	
	@Test
	public void testLongestCommonPrefix2() {
		String[] strs = {"a"};
		String result = s.longestCommonPrefix(strs);
		System.out.println(result);
		assertTrue("a".equals(result));
	}
	
	@Test
	public void testLongestCommonPrefix3() {
		String[] strs = {""};
		String result = s.longestCommonPrefix(strs);
		System.out.println(result);
		assertTrue("".equals(result));
	}
	
	@Test
	public void testLongestCommonPrefix4() {
		String[] strs = {"", "a"};
		String result = s.longestCommonPrefix(strs);
		System.out.println(result);
		assertTrue("".equals(result));
	}

}

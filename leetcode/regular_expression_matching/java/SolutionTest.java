import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		boolean result = s.isMatch("aa", "aa");
		assertTrue(result);
	}
	
	@Test
	public void test2() {
		boolean result = s.isMatch("aa", "a");
		assertFalse(result);
	}
	
	@Test
	public void test3() {
		boolean result = s.isMatch("aaa","aa");
		assertFalse(result);
	}
	
	@Test
	public void test4() {
		boolean result = s.isMatch("aa", "a*");
		assertTrue(result);
	}
	
	@Test
	public void test5() {
		boolean result = s.isMatch("aa", ".*");
		assertTrue(result);
	}
	
	@Test
	public void test6() {
		boolean result = s.isMatch("ab", ".*");
		assertTrue(result);
	}
	
	@Test
	public void test7() {
		boolean result = s.isMatch("aab", "c*a*b");
		assertTrue(result);
	}
	
	@Test
	public void test8() {
		boolean result = s.isMatch("**", "**");
		assertTrue(result);
		
		result = s.isMatch("", "a");
		assertFalse(result);
		
		result = s.isMatch("", ".");
		assertFalse(result);
		
		result = s.isMatch("", ".*");
		assertTrue(result);
		
		result = s.isMatch("", "a*");
		assertTrue(result);
	}
	
	@Test
	public void test9() {
		boolean result = s.isMatch("aaa", "ab*ac*a");
		assertTrue(result);
		
		result = s.isMatch("aaa", "ab*a*c*a");
		assertTrue(result);
		
		result = s.isMatch("aaca", "ab*a*c*a");
		assertTrue(result);
		
		result = s.isMatch("a", "ab*");
		assertTrue(result);
		
		result = s.isMatch("abbbcd", "ab*bbbcd");
		assertTrue(result);
	}
	
	@Test
	public void test10() {
		boolean result = s.isMatch("aaa", "ab*a");
		assertFalse(result);
		
		result = s.isMatch("aaba", "ab*a*c*a");
		assertFalse(result);
	}
}

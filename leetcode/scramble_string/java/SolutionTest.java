import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		boolean result = s.isScramble("a", "a");
		assertTrue(result);
	}
	
	@Test
	public void test2() {
		boolean result = s.isScramble("at", "at");
		assertTrue(result);
	}
	
	@Test
	public void test3() {
		boolean result = s.isScramble("at", "ta");
		assertTrue(result);
	}
	
	@Test
	public void test4() {
		boolean result = s.isScramble("at", "aa");
		assertFalse(result);
	}
	
	@Test 
	public void test5() {
		boolean result = s.isScramble("rgtae", "great");
		assertTrue(result);
	}
	
	@Test
	public void test6() {
		boolean result = s.isScramble("rgtae", "great");
		assertTrue(result);
	}
	
	@Test
	public void test7() {
		boolean result = s.isScramble("gtear", "great");
		assertTrue(result);
	}
	
	@Test
	public void test8() {
		boolean result = s.isScramble("abcdefghijklmnopq", "efghijklmnopqcadb");
		assertFalse(result);
	}
}

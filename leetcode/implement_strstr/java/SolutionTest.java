import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	@Test
	public void testStrStr1() {
		String T = "abababacaba";
		String P = "ababaca";
		String match = s.strStr(T, P);
		System.out.println(match);
		assertTrue(match != null);
	}
	
	@Test
	public void testStrStr2() {
		String T = "";
		String P = "";
		String match = s.strStr(T, P);
		System.out.println(match);
		assertTrue(match != null);
	}
}

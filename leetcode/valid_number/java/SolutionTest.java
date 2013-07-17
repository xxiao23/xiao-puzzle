import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test() {
		assertTrue(s.isNumber("0"));
		assertTrue(s.isNumber("123"));
		assertTrue(s.isNumber("0.1"));
		assertFalse(s.isNumber("abc"));
		assertFalse(s.isNumber("1 a"));
		assertTrue(s.isNumber("2e10"));
		assertTrue(s.isNumber("   0.1   "));
		assertTrue(s.isNumber("+1."));
		assertTrue(s.isNumber(".34"));
		assertFalse(s.isNumber("a1"));
		assertTrue(s.isNumber("010"));
		assertTrue(s.isNumber("0."));
		assertFalse(s.isNumber("."));
		assertTrue(s.isNumber("1.3e2"));
	}

}

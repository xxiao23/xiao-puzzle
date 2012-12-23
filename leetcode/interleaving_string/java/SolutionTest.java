import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testIsInterleave() {
		String a = "aabcc";
		String b = "dbbca";
		String c = "aadbbcbcac";
		boolean result = s.isInterleave(a, b, c);
		assertTrue(result);
		
		c = "aadbbbaccc";
		result = s.isInterleave(a, b, c);
		assertFalse(result);
	}

}

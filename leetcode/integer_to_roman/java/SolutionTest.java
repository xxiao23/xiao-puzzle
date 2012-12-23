import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testIntToRoman() {
		String result = s.intToRoman(1);
		System.out.println(result);
		assertTrue(result.equals("I"));
		
		result = s.intToRoman(123);
		System.out.println(result);
		assertTrue(result.equals("CXXIII"));
	}

}

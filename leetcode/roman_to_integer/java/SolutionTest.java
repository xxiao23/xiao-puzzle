import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test() {
		int result = s.romanToInt("I");
		assertTrue("result = " + result, result == 1);
		
		result = s.romanToInt("II");
		assertTrue("result = " + result, result == 2);
		
		result = s.romanToInt("IV");
		assertTrue("result = " + result, result == 4);
		
		result = s.romanToInt("IIX");
		assertTrue("result = " + result, result == 8);  
		
		result = s.romanToInt("MMXIII");
		assertTrue("result = " + result, result == 2013);
	}

}

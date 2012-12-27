import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLengthOfLastWord1() {
		String str = "Hello World";
		int result = s.lengthOfLastWord(str);
		assertTrue(result == 5);
	}
	
	@Test
	public void testLengthOfLastWord2() {
		String str = "Hello World ";
		int result = s.lengthOfLastWord(str);
		assertTrue(result == 5);
	}

}

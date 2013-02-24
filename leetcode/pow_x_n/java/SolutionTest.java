import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test() {
		double x = s.pow(2, 0);
		assertTrue(x == 1.0);
		
		x = s.pow(2, 1);
		assertTrue(x == 2.0);
		
		x = s.pow(2, 2);
		assertTrue(x == 4.0);
		
		x = s.pow(2, -1);
		assertTrue(x == 0.5);
		
		x = s.pow(2, -2);
		assertTrue(x == 0.25);
	}

}

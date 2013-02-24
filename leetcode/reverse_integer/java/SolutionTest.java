import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int r = s.reverse(123);
		assertTrue(r == 321);
		
		r = s.reverse(-123);
		assertTrue(r == -321);
		
		r = s.reverse(10);
		assertTrue(r == 1);
		
		r = s.reverse(-10);
		assertTrue(r == -1);
	}
	
	@Test
	public void test2() {
		int r = s.reverse(1000000003);
		System.out.println(r);
	}

}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		for (int i = 0; i <= 100; ++i) {
			int result = s.sqrt(i);
			System.out.println(i + " : " + result);
			assertTrue(Integer.valueOf(result).toString(), result == (int)Math.sqrt(i));
		}
	}
	
	@Test
	public void test2() {
		int n = 2147395599;
		int result = s.sqrt(n);
		System.out.println(result);
		System.out.println((int)Math.sqrt(n));
		assertTrue(Integer.valueOf(result).toString(), result == (int)Math.sqrt(n));
	}

}

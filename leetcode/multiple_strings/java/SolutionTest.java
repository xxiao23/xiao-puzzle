import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMultiply1() {
		String num1 = "123";
		String num2 = "456";
		String result = s.multiply(num1, num2);
		System.out.println(result);
		assertTrue("56088".equals(result));
	}
	
	@Test
	public void testMultiply2() {
		String num1 = "13219380";
		String num2 = "0";
		String result = s.multiply(num1, num2);
		System.out.println(result);
		assertTrue("0".equals(result));
	}
	
	@Test
	public void testMultiply3() {
		String num1 = "9";
		String num2 = "9";
		String result = s.multiply(num1, num2);
		System.out.println(result);
		assertTrue("81".equals(result));
	}

}

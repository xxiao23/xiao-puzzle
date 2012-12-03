import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testDivide() {
		int dividend = 9;
		int divisor = 3;
		Solution solution = new Solution();
		int result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == 3);
		
		dividend = 8;
		divisor = 3;
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == 2);
		
		dividend = -9;
		divisor = 3;
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == -3);
		
		dividend = 8;
		divisor = -3;
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == -2);
		
		dividend = 0;
		divisor = -3;
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == 0);
		
		//2147483647, 3
		dividend = 2147483647;
		divisor = 3;
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == 715827882);
		
		dividend = -2147483648;
		divisor = 1;
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == -2147483648);
	}
	
	@Test
	public void testDivide2() {
		int dividend = -2147483648;
		int divisor = 1;
		Solution solution = new Solution();
		int result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == -2147483648);
		
		//2147483647, 2	
		dividend = 2147483647;
		divisor = 2;
		solution = new Solution();
		result = solution.divide(dividend, divisor);
		System.out.println(result);
		assertTrue(result == 1073741823);
	}
	
}

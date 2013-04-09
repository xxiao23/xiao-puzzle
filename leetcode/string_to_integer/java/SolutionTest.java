import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int result = s.atoi("+100000");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == 100000);
		
		result = s.atoi("   +100000   ");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == 100000);
		
		result = s.atoi("  -123456 ");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == -123456);
		
		result = s.atoi("123456789123456789123456789");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == Integer.MAX_VALUE);
		
		result = s.atoi("-123456789123456789123456789");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == Integer.MIN_VALUE);
		
		result = s.atoi("");
		System.out.println(result);
		assertTrue(result == 0);
		
		result = s.atoi("     ");
		System.out.println(result);
		assertTrue(result == 0);
		
		result = s.atoi("2147483648");
		System.out.println(result);
		assertTrue(result == Integer.MAX_VALUE);
		
		result = s.atoi("-2147483649");
		System.out.println(result);
		assertTrue(result == Integer.MIN_VALUE);
		
	}
	
	@Test
	public void test2() {
		int result = s.atoi("      +107834r68276");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == 107834);
	}
	
	@Test
	public void test3() {
		int result = s.atoi("         +10523538441s");
		System.out.println(result);
		assertTrue(Integer.valueOf(result).toString(), result == 2147483647);
	}

}

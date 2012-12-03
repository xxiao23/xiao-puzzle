import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testNumDecodings() {
		Solution solution = new Solution();
		String s = "12";
		int result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 2);
		s = "122";
		result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 3);
		s = "127";
		result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 2);
		s = "";
		result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 0);
		s = "10";
		result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 1);
		s = "0";
		result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 0);
	}
	
	@Test 
	public void testNumDecodings1() {
		Solution solution = new Solution();
		String s = "00";
		int result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 0);
		s = "11";
		result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 2);
	}
	
	@Test 
	public void testNumDecodings2() {
		Solution solution = new Solution();
		String s = "01";
		int result = solution.numDecodings(s);
		System.out.println(result);
		assertTrue(result == 0);
//		s = "11";
//		result = solution.numDecodings(s);
//		System.out.println(result);
//		assertTrue(result == 2);
	}

}

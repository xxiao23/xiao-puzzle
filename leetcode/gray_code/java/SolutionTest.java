import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testGrayCode0() {
		int n = 0;
		ArrayList<Integer> result = s.grayCode(n);
		System.out.println(result);
		assertTrue(result.size() == 0);
	}

	@Test
	public void testGrayCode1() {
		int n = 1;
		ArrayList<Integer> result = s.grayCode(n);
		System.out.println(result);
		assertTrue(result.size() == 2);
		assertTrue(result.get(0) == 0);
	}

	@Test
	public void testGrayCode2() {
		int n = 2;
		ArrayList<Integer> result = s.grayCode(n);
		System.out.println(result);
		assertTrue(result.size() == 4);
		assertTrue(result.get(0) == 0);
	}
	
	@Test
	public void testGrayCode3() {
		int n = 3;
		ArrayList<Integer> result = s.grayCode(n);
		System.out.println(result);
		assertTrue(result.size() == 8);
		assertTrue(result.get(0) == 0);
	}

}

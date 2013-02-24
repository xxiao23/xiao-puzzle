import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	@Test
	public void test1() {
		int[] A = {1, 1, 1, 2, 2, 3};
		int result = s.removeDuplicates(A);
		System.out.println(Arrays.toString(A));
		assertTrue("result = " + result, result == 5);
	}
	
	@Test
	public void test2() {
		int[] A = {1, 1, 1, 2, 2, 3, 3};
		int result = s.removeDuplicates(A);
		System.out.println(Arrays.toString(A));
		assertTrue("result = " + result, result == 6);
	}
	
	@Test
	public void test3() {
		int[] A = {1, 1, 1, 2, 2, 3, 3, 3};
		int result = s.removeDuplicates(A);
		System.out.println(Arrays.toString(A));
		assertTrue("result = " + result, result == 6);
	}	

}

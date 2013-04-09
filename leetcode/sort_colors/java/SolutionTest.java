import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	@Test
	public void test1() {
		int[] A = {0, 0, 0};
		s.sortColors(A);
		System.out.println(Arrays.toString(A));
		assertTrue(A[0] == 0);
	}
	
	@Test
	public void test2() {
		int[] A = {1, 1, 1};
		s.sortColors(A);
		System.out.println(Arrays.toString(A));
		assertTrue(A[0] == 1);
	}
	
	@Test
	public void test3() {
		int[] A = {2, 2, 2};
		s.sortColors(A);
		System.out.println(Arrays.toString(A));
		assertTrue(A[0] == 2);
	}
	
	@Test
	public void test4() {
		int[] A = {0, 1, 2, 0, 2, 1, 2};
		s.sortColors(A);
		System.out.println(Arrays.toString(A));
		assertTrue(A[0] == 0);
		assertTrue(A[2] == 1);
		assertTrue(A[6] == 2);
	}

}

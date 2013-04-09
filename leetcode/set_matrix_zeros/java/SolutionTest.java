import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[][] A = {
				{1, 0, 1},
				{0, 1, 1},
				{1, 1, 1}
		};
		s.setZeroes(A);
		for(int i = 0; i < A.length; ++i) {
			System.out.println(Arrays.toString(A[i]));
		}
		assertTrue(A[1][1] == 0);
		assertTrue(A[0][0] == 0);
		assertTrue(A[2][2] != 0);
	}
	
	@Test
	public void test2() {
		int [][] A = {
				{1, 0, 1}
		};
		s.setZeroes(A);
		System.out.println(Arrays.toString(A[0]));
		assertTrue(A[0][0] == 0);
	}

}

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
			
	@Test
	public void test1() {
		int[][] m1 = {{1,2}, {3,4}};
		s.rotate(m1);
		for (int i = 0; i < m1.length; ++i) {
			System.out.println(Arrays.toString(m1[i]));
		}
		assertTrue(m1[0][0] == 3);
		assertTrue(m1[1][1] == 2);
	}

	@Test
	public void test2() {
		int[][] m1 = {{1,2,3}, {4,5,6}, {7,8,9}};
		s.rotate(m1);
		for (int i = 0; i < m1.length; ++i) {
			System.out.println(Arrays.toString(m1[i]));
		}
		assertTrue(m1[0][0] == 7);
		assertTrue(m1[2][2] == 3);
	}
}

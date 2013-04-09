import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[][] matrix = s.generateMatrix(3);
		for (int i = 0; i < 3; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		assertTrue(matrix[0][0] == 1);
		assertTrue(matrix[1][1] == 9);
	}
	
	@Test
	public void test2() {
		int[][] matrix = s.generateMatrix(2);
		for (int i = 0; i < 2; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		assertTrue(matrix[0][0] == 1);
		assertTrue(matrix[1][1] == 3);
	}

	@Test
	public void test3() {
		int[][] matrix = s.generateMatrix(1);
		for (int i = 0; i < 1; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		assertTrue(matrix[0][0] == 1);
	}
}

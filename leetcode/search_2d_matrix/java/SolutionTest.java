import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[][] matrix = { {1, 3, 5, 7},
						   {10, 11, 16, 20},
						   {23, 30, 34, 50}
						 };
		boolean result = s.searchMatrix(matrix, 3);
		assertTrue(result);
		
		result = s.searchMatrix(matrix, 0);
		assertFalse(result);
	}
	
	@Test
	public void test2() {
		int[][] matrix = { {1, 3, 5, 7}
						 };
		boolean result = s.searchMatrix(matrix, 3);
		assertTrue(result);
		
		result = s.searchMatrix(matrix, 0);
		assertFalse(result);
	}

	@Test
	public void test3() {
		int[][] matrix = { {1},
						   {10},
						   {23}
						 };
		boolean result = s.searchMatrix(matrix, 1);
		assertTrue(result);
		
		result = s.searchMatrix(matrix, 0);
		assertFalse(result);
	}
	
	@Test
	public void test4() {
		int[][] matrix = { {1, 3, 5, 7},
						   {10, 11, 16, 20},
						   {23, 30, 34, 50}
						 };
		boolean result = s.searchMatrix(matrix, 11);
		assertTrue(result);
	}
}

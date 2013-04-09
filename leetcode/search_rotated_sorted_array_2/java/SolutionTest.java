import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] A = {4, 5, 6, 7, 0, 1, 2, 4};
		boolean result = false;
		for (int i = 0; i < A.length; ++i) {
			result = s.search(A, A[i]);
			assertTrue(result);
		}
		
		assertFalse(s.search(A, -1));
		assertFalse(s.search(A, 3));
		assertFalse(s.search(A, 10));
	}

	@Test
	public void test2() {
		int[] A = {0, 0, 0, 0};
		for (int i = 0; i < A.length; ++i) {
			assertTrue(s.search(A, A[i]));
		}
		
		assertFalse(s.search(A, -1));
		assertFalse(s.search(A, 2));
	}
	
	@Test
	public void test3() {
		int[] A = {1, 1, 3, 1};
		for (int i = 0; i < A.length; ++i) {
			assertTrue(s.search(A, A[i]));
		}
	}
}

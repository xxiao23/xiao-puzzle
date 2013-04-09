import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] A = {4, 5, 6, 7, 0, 1, 2};
		int result = -1;
		for (int i = 0; i < A.length; ++i) {
			result = s.search(A, A[i]);
			assertTrue(result == i);
		}
		
		result = s.search(A, 3);
		assertTrue(result == -1);	
	}
	
	@Test
	public void test2() {
		int[] A = {0, 1, 2, 4, 5, 6, 7};
		int result = -1;
		for (int i = 0; i < A.length; ++i) {
			result = s.search(A, A[i]);
			assertTrue(result == i);
		}
		
		result = s.search(A, 3);
		assertTrue(result == -1);	
	}

}

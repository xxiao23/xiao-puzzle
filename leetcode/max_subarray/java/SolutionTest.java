import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMaxSubArray() {
		int[] A = {0};
		int result = s.maxSubArray(A);
		assertTrue(result == 0);
		
		int[] B = {-1};
		result = s.maxSubArray(B);
		assertTrue(result == -1);
		
		int[] C = {-1, 2};
		result = s.maxSubArray(C);
		assertTrue(result == 2);
		
		int[] D = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		result = s.maxSubArray(D);
		assertTrue(result == 6);
	}

}

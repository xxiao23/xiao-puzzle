import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testFirstMissingPositive() {
		int[] A = {1,2,0};
		Solution solution = new Solution();
		int result = solution.firstMissingPositive(A);
		System.out.println(result);
		assertTrue(result == 3);
		
		int[] B = {3,4,-1,1};
		result = solution.firstMissingPositive(B);
		System.out.println(result);
		assertTrue(result == 2);
		
		int[] C = {};
		result = solution.firstMissingPositive(C);
		System.out.println(result);
		assertTrue(result == 1);
		
		int[] D = {1,1};
		result = solution.firstMissingPositive(D);
		System.out.println(result);
		assertTrue(result == 2);
	}

}

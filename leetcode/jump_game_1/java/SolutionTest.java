import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testCanJump() {
		int[] A = {2,3,1,1,4};
		boolean result = s.canJump(A);
		assertTrue(result);
		
		int[] B = {2,0};
		result = s.canJump(B);
		assertTrue(result);
		
		int[] C = {2,0,6,9,8,4,5,0,8,9,1,2,9,6,8,8,0,6,3,1,2,2,1,2,6,5,3,1,2,2,6,4,2,4,3,0,0,0,3,8,2,4,0,1,2,0,1,4,6,5,8,0,7,9,3,4,6,6,5,8,9,3,4,3,7,0,4,9,0,9,8,4,3,0,7,7,1,9,1,9,4,9,0,1,9,5,7,7,1,5,8,2,8,2,6,8,2,2,7,5,1,7,9,6};
		result = s.canJump(C);
		assertFalse(result);
		
		int[] D = {1,2,2,6,3,6,1,8,9,4,7,6,5,6,8,2,6,1,3,6,6,6,3,2,4,9,4,5,9,8,2,2,1,6,1,6,2,2,6,1,8,6,8,3,2,8,5,8,0,1,4,8,7,9,0,3,9,4,8,0,2,2,5,5,8,6,3,1,0,2,4,9,8,4,4,2,3,2,2,5,5,9,3,2,8,5,8,9,1,6,2,5,9,9,3,9,7,6,0,7,8,7,8,8,3,5,0};
		result = s.canJump(D);
		System.out.println(result);
				
		int[] E = new int[10000];
		for(int i = 0; i < 10000; ++i) {
			E[i] = 10000-i;
		}
		result = s.canJump(E);
		System.out.println(result);
	}

}

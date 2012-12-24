import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	@Test
	public void testJump() {
		int[] A = {2,3,1,1,4};
		int result = s.jump(A);
		assertTrue("result = " + result, result == 2);
		
		int[] B = {1,2,1,1,1};
		result = s.jump(B);
		assertTrue("result = " + result, result == 3);
		
		int[] AA = {7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
		result = s.jump(AA);
		assertTrue("result = " + result, result == 2); 
				
		int[] D = {1,2,2,6,3,6,1,8,9,4,7,6,5,6,8,2,6,1,3,6,6,6,3,2,4,9,4,5,9,8,2,2,1,6,1,6,2,2,6,1,8,6,8,3,2,8,5,8,0,1,4,8,7,9,0,3,9,4,8,0,2,2,5,5,8,6,3,1,0,2,4,9,8,4,4,2,3,2,2,5,5,9,3,2,8,5,8,9,1,6,2,5,9,9,3,9,7,6,0,7,8,7,8,8,3,5,0};
		result = s.jump(D);
		System.out.println(result);
		assertTrue(result > 0);
				
		int[] E = new int[10000];
		for(int i = 0; i < 10000; ++i) {
			E[i] = 10000-i;
		}
		result = s.jump(E);
		System.out.println(result);
	}

}

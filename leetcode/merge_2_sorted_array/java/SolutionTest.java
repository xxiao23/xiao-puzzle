import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMerge1() {
		int[] A = new int[6];
		int[] B = new int[3];
		
		A[0] = 1;
		A[1] = 3;
		A[2] = 5;
		B[0] = 2;
		B[1] = 4;
		B[2] = 6;
		
		s.merge(A, 3, B, 3);
		System.out.println(A.toString());
		assertTrue(A[5] == 6);
	}

	@Test
	public void testMerge2() {
		int[] A = new int[7];
		int[] B = new int[4];
		
		A[0] = 1;
		A[1] = 3;
		A[2] = 5;
		B[0] = 2;
		B[1] = 4;
		B[2] = 6;
		B[3] = 7;
		
		s.merge(A, 3, B, 4);
		System.out.println(A.toString());
		assertTrue(A[6] == 7);
	}
	
	@Test
	public void testMerge3() {
		int[] A = new int[7];
		int[] B = new int[3];
		
		A[0] = 1;
		A[1] = 3;
		A[2] = 5;
		A[3] = 7;
		B[0] = 2;
		B[1] = 4;
		B[2] = 6;
		
		s.merge(A, 4, B, 3);
		System.out.println(A.toString());
		assertTrue(A[6] == 7);
	}
}

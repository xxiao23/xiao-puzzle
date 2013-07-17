import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] A = {0,1,0,2,1,0,1,3,2,1,2,1};
		int result = s.trap(A);
		assertTrue(result+"", result == 6);
	}
	
	@Test
	public void test2() {
		int[] A = {1, 2, 3};
		int result = s.trap(A);
		assertTrue(result+"", result == 0);
	}
	
	@Test
	public void test3() {
		int[] A = {3, 2, 1};
		int result = s.trap(A);
		assertTrue(result+"", result == 0);
	}
	
	@Test
	public void test4() {
		int[] A = {2,0,2};
		int result = s.trap(A);
		assertTrue(result+"", result == 2);
	}
	
	@Test
	public void test5() {
		int[] A = {4,2,3};
		int result = s.trap(A);
		assertTrue(result+"", result == 1);
		
		int[] B = {2,1,0,2};
		result = s.trap(B);
		assertTrue(result+"", result == 3);
	}
	
	@Test
	public void test6() {
		int[] A = {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3};
		int result = s.trap(A);
		assertTrue(result+"", result == 83);
	}
	
	@Test
	public void test7() {
		int[] A = {4,2,0,3,2,5};
		int result =s.trap(A);
		assertTrue(result+"", result == 9);
	}
}

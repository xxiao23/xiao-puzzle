import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] A = {1,3,5,6};
		int result = s.searchInsert(A, 5);
		assertTrue(""+result, result == 2);
		
		result = s.searchInsert(A, 2);
		assertTrue(""+result, result == 1);
		
		result = s.searchInsert(A, 7);
		assertTrue(""+result, result==4);
		
		result = s.searchInsert(A, 0);
		assertTrue(""+result, result==0);
	}
	
	@Test
	public void test2() {
		int[] A = {1,1,2,2};
		
		int result = s.searchInsert(A, 1);
		assertTrue(""+result, result == 1);
		
		result = s.searchInsert(A, 0);
		assertTrue(""+result, result == 0);
		
		result = s.searchInsert(A, 3);
		assertTrue(""+result, result == 4);
	}
}

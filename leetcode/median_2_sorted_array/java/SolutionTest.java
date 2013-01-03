import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testFindMedianSortedArrays1() {
		int[] A1 = {};
		int[] B1 = {1};
		double result = s.findMedianSortedArrays(A1, B1);
		assertTrue("result = " + result, result == 1.0);
		
		int[] A2 = {2};
		int[] B2 = {};
		result = s.findMedianSortedArrays(A2, B2);
		assertTrue("result = " + result, result == 2.0);
		
		int[] A3 = {2,3,4,5};
		int[] B3 = {};
		result = s.findMedianSortedArrays(A3, B3);
		assertTrue("result = " + result, result == 3.5);
		
	}
	
	@Test
	public void testFindMedianSortedArrays2() {
		int[] A4 = {1, 1, 1};
		int[] B4 = {1, 1, 1};
		double result = s.findMedianSortedArrays(A4, B4);
		assertTrue("result = " + result, result == 1.0); 
	}
	
	@Test
	public void testFindMedianSortedArrays3() {
		int[] A = {3};
		int[] B = {1,2,4,5};
		double result = s.findMedianSortedArrays(A, B);
		assertTrue("result = " + result, result == 3.0);
	}
	
	@Test
	public void testFindMedianSortedArrays4() {
		int[] A = {1,2,3,5};
		int[] B = {4,6,7,8};
		double result = s.findMedianSortedArrays(A, B);
		assertTrue("result = " + result, result == 4.5);
	}
	
	@Test
	public void testFindMedianSortedArrays5() {
		int[] A = {1,2,3,5};
		int[] B = {4,6,7};
		double result = s.findMedianSortedArrays(A, B);
		assertTrue("result = " + result, result == 4.0);
	}

	@Test
	public void testFindMedianSortedArrays6() {
		int[] A = {3,5,7};
		int[] B = {1,2,4,6,8};
		double result = s.findMedianSortedArrays(A, B);
		assertTrue("result = " + result, result == 4.5);
	}
	
	@Test
	public void testFindMedianSortedArrays7() {
		int[] A = {1};
		int[] B = {2,3,4};
		double result = s.findMedianSortedArrays(A, B);
		assertTrue("result = " + result, result == 2.5);
	}
	
}

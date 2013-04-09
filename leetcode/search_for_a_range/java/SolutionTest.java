import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] A = {5, 7, 7, 8, 8, 10};
		int[] result = s.searchRange(A, 8);
		assertTrue(result[0] == 3);
		assertTrue(result[1] == 4);
		
		result = s.searchRange(A, 5);
		assertTrue(result[0] == 0);
		assertTrue(result[1] == 0);
		
		result = s.searchRange(A, 4);
		assertTrue(result[0] == -1);
		assertTrue(result[1] == -1);
		
		result = s.searchRange(A, 11);
		assertTrue(result[0] == -1);
		assertTrue(result[1] == -1);
		
		result = s.searchRange(A, 10);
		assertTrue(result[0] == 5);
		assertTrue(result[1] == 5);
	}
	
}

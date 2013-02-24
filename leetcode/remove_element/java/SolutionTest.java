import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] A = { 2, 3, 3 };
		int result = s.removeElement(A, 2);
		System.out.println(Arrays.toString(A));
		assertTrue("result = " + result, result == 2);
	}

}

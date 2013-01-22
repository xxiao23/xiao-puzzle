import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testNextPermutation1() {
		int[] num = {1, 2, 3};
		s.nextPermutation(num);
		assertTrue("num : " + Arrays.toString(num), Arrays.equals(num, new int[]{1,3,2}));
	}
	
	@Test
	public void testNextPermutation2() {
		int[] num = {3, 2, 1};
		s.nextPermutation(num);
		assertTrue("num : " + Arrays.toString(num), Arrays.equals(num, new int[]{1,2,3}));
	}
	
	@Test
	public void testNextPermutation3() {
		int[] num = {1, 1, 5};
		s.nextPermutation(num);
		assertTrue("num : " + Arrays.toString(num), Arrays.equals(num, new int[]{1,5,1}));
	}
	
	@Test
	public void testNextPermutation4() {
		int[] num = {1, 3, 2, 5, 4};
		s.nextPermutation(num);
		assertTrue("num : " + Arrays.toString(num), Arrays.equals(num, new int[]{1,3,4,2,5}));
	}
	
	@Test
	public void testNextPermutation5() {
		int[] num = {5, 1, 1};
		s.nextPermutation(num);
		assertTrue("num : " + Arrays.toString(num), Arrays.equals(num, new int[]{1,1,5}));
	}

}

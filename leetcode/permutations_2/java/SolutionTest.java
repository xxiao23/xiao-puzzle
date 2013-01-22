import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testPermuteUnique() {
		int[] num = new int[] {1, 1, 2};
		ArrayList<ArrayList<Integer>> result = s.permuteUnique(num);
		System.out.println(result);
		assertTrue(result.size() == 3);
		
		int[] num2 = new int[] {2, 2, 2, 2};
		result = s.permuteUnique(num2);
		System.out.println(result);
		assertTrue(result.size() == 1);
	}

}

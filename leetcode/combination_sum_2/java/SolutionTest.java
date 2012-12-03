import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testCombinationSum21() {
		int[] num = {10,1,2,7,6,1,5};
		int target = 8;
		Solution solution = new Solution();
		ArrayList<ArrayList<Integer>> result = solution.combinationSum2(num, target);
		System.out.println(result);
		assertNotNull(result);
	}

}

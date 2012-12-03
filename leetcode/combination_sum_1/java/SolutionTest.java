import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testCombinationSum() {
		int[] candidates = {2, 3, 6, 7};
		int target = 7;
		ArrayList<ArrayList<Integer>> result = new Solution().combinationSum(candidates, target);
		System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void testCombinationSum1() {
		int[] candidates = {1};
		int target = 1;
		ArrayList<ArrayList<Integer>> result = new Solution().combinationSum(candidates, target);
		System.out.println(result);
		assertNotNull(result);
	}

	@Test
	public void testCombinationSum2() {
		int[] candidates = {1, 2};
		int target = 4;
		ArrayList<ArrayList<Integer>> result = new Solution().combinationSum(candidates, target);
		System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void testCombinationSum3() {
		int[] candidates = {2, 3, 5};
		int target = 7;
		ArrayList<ArrayList<Integer>> result = new Solution().combinationSum(candidates, target);
		System.out.println(result);
		assertNotNull(result);
	}
	
	@Test
	public void testCombinationSum4() {
		int[] candidates = {8,6,4,12,5,7,3,11};
		int target = 28;
		ArrayList<ArrayList<Integer>> result = new Solution().combinationSum(candidates, target);
		System.out.println(result);
		assertNotNull(result);
	}
}

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] S = { 1, 2, 3 };
		ArrayList<ArrayList<Integer>> result = s.subsets(S);
		System.out.println(result);
		assertTrue(result.size() == 8);
	}
	
	@Test
	public void test2() {
		int[] S = { 1, 2, 3, 4 };
		ArrayList<ArrayList<Integer>> result = s.subsets(S);
		System.out.println(result);
		assertTrue(result.size() == 16);
	}

}

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[] num = new int[] {1, 2, 3};
		ArrayList<ArrayList<Integer>> result = s.permute(num);
		System.out.println(result);
		assertTrue(result.size() == 6);
	}
	
	@Test
	public void test2() {
		int[] num = new int[] {1, 1, 2};
		ArrayList<ArrayList<Integer>> result = s.permute(num);
		System.out.println(result);
		assertTrue(result.size() == 6);
	}

}

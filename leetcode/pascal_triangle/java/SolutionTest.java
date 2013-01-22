import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testGenerate1() {
		ArrayList<ArrayList<Integer>> result = s.generate(0);
		System.out.println(result);
		assertTrue(result.size() == 0);
	}

	@Test
	public void testGenerate2() {
		ArrayList<ArrayList<Integer>> result = s.generate(1);
		System.out.println(result);
		assertTrue(result.size() == 1);
	}
	
	@Test
	public void testGenerate3() {
		ArrayList<ArrayList<Integer>> result = s.generate(2);
		System.out.println(result);
		assertTrue(result.size() == 2);
	}
	
	@Test
	public void testGenerate4() {
		ArrayList<ArrayList<Integer>> result = s.generate(3);
		System.out.println(result);
		assertTrue(result.size() == 3);
	}
}

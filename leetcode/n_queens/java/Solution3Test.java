import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class Solution3Test {

	Solution3 s = new Solution3();
	
	
	@Test
	public void testSolveNQueens1() {
		ArrayList<String[]> result = s.solveNQueens(0);
		assertNotNull(result);
		assertTrue(result.size() == 0);
	}
	
	@Test
	public void testSolveNQueens2() {
		ArrayList<String[]> result = s.solveNQueens(1);
		for (String[] sa : result) {
			System.out.println(Arrays.asList(sa));
		}
		assertNotNull(result);
		assertTrue(result.size() == 1);
	}
	
	@Test
	public void testSolveNQueens3() {
		ArrayList<String[]> result = s.solveNQueens(4);
		for (String[] sa : result) {
			System.out.println(Arrays.asList(sa));
		}		
		assertNotNull(result);
		assertTrue(result.size() == 2);
	}

	@Test
	public void testSolveNQueens4() {
		ArrayList<String[]> result = s.solveNQueens(9);
		for (String[] sa : result) {
			System.out.println(Arrays.asList(sa));
		}		
		assertNotNull(result);
		assertTrue("number of distinct solutions " + result.size(), result.size() == 352);
	}


}

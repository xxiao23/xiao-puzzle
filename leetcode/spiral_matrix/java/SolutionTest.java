import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		ArrayList<Integer> result = s.spiralOrder(matrix);
		System.out.println(result.toString());
		assertTrue(result.get(0) == 1);
		assertTrue(result.get(8) == 5);
	}
	
	@Test
	public void test2() {
		int[][] matrix = {
				{1, 2},
				{4, 5},
				{7, 8}
		};
		ArrayList<Integer> result = s.spiralOrder(matrix);
		System.out.println(result.toString());
		assertTrue(result.get(0) == 1);
		assertTrue(result.get(5) == 4);
	}
	
	@Test
	public void test3() {
		int[][] matrix = {
				{1, 2},
				{3, 4}
		};
		ArrayList<Integer> result = s.spiralOrder(matrix);
		System.out.println(result.toString());
		assertTrue(result.get(0) == 1);
		assertTrue(result.get(3) == 3);
	}
	
	@Test
	public void test4() {
		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6}
		};
		ArrayList<Integer> result = s.spiralOrder(matrix);
		System.out.println(result.toString());
		assertTrue(result.get(0) == 1);
		assertTrue(result.get(5) == 4);
	}

}

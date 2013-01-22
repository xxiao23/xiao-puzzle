import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMinimumTotal1() {
		ArrayList<ArrayList<Integer>> t = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> r1 = new ArrayList<Integer>();
		r1.add(2);
		ArrayList<Integer> r2 = new ArrayList<Integer>();
		r2.add(3);
		r2.add(4);
		ArrayList<Integer> r3 = new ArrayList<Integer>();
		r3.add(6);
		r3.add(5);
		r3.add(7);
		ArrayList<Integer> r4 = new ArrayList<Integer>();
		r4.add(4);
		r4.add(1);
		r4.add(8);
		r4.add(3);
		
		t.add(r1);
		t.add(r2);
		t.add(r3);
		t.add(r4);
		
		int result = s.minimumTotal(t);
		assertTrue("result = " + result, result == 11);
	}
	
	@Test
	public void testMinimumTotal2() {
		ArrayList<ArrayList<Integer>> t= new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> r1 = new ArrayList<Integer>();
		r1.add(1);
		t.add(r1);
		
		int result = s.minimumTotal(t);
		assertTrue("result = " + result, result == 1);
	}
}

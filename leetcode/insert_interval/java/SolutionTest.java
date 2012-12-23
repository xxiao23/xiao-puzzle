import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();

	@Test
	public void testInsert1() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,3));
		intervals.add(new Interval(6,9));
		Interval newInterval = new Interval(2, 5);
		ArrayList<Interval> result = s.insert(intervals, newInterval);
		System.out.println(result);
		assertTrue(result.size() == 2);
	}

	@Test
	public void testInsert2() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,2));
		intervals.add(new Interval(3,5));
		intervals.add(new Interval(6,7));
		intervals.add(new Interval(8, 10));
		intervals.add(new Interval(12, 16));
		Interval newInterval = new Interval(4, 9);
		ArrayList<Interval> result = s.insert(intervals, newInterval);
		System.out.println(result);
		assertTrue(result.size() == 3);
	}
	
	@Test
	public void testInsert3() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		Interval newInterval = new Interval(5, 7);
		ArrayList<Interval> result = s.insert(intervals, newInterval);
		System.out.println(result);
		assertTrue(result.size() == 1);
	}
	
	@Test
	public void testInsert4() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1,5));
		Interval newInterval = new Interval(2, 3);
		ArrayList<Interval> result = s.insert(intervals, newInterval);
		System.out.println(result);
		assertTrue(result.size() == 1);
	}

}

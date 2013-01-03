import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMerge() {
		// Given [1,3],[2,6],[8,10],[15,18],
		// return [1,6],[8,10],[15,18].
		Interval[] ia = new Interval[4];
		ia[0] = new Interval(1,3);
		ia[1] = new Interval(2,6);
		ia[2] = new Interval(8,10);
		ia[3] = new Interval(15,18);
		ArrayList<Interval> result = s.merge(new ArrayList<Interval>(Arrays.asList(ia)));
		System.out.println(result);
		assertTrue(result != null);
		assertTrue(result.size() == 3);
		
		result = s.merge(new ArrayList<Interval>());
		System.out.println(result);
		assertTrue(result.size() == 0);
	}

}

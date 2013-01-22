import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testGetRow() {
		ArrayList<Integer> result = s.getRow(0);
		System.out.println(result);
		assertTrue(result.size() == 1);
		
		result = s.getRow(3);
		System.out.println(result);
		assertTrue(result.size() == 4);
	}

}

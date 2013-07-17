import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		assertTrue(s.numTrees(0) == 1);
		assertTrue(s.numTrees(1) == 1);
		assertTrue(s.numTrees(2) == 2);
		assertTrue(s.numTrees(3) == 5);
	}

}

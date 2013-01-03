import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMaxDepth1() {
		int result = s.maxDepth(null);
		assertTrue(result == 0);
	}
	
	@Test
	public void testMaxDepth2() {
		TreeNode root = new TreeNode(0);
		int result = s.maxDepth(root);
		assertTrue(result == 1);
	}
	
	@Test
	public void testMaxDepth3() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		n0.left = n1;
		int result = s.maxDepth(n0);
		assertTrue(result == 2);
	}
}

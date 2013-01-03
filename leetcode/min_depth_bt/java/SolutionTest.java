import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMinDepth1() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		n0.left = n1;
		int result = s.minDepth(n0);
		assertTrue("result = " + result, result == 2);
	}

	@Test
	public void testMinDepth2() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		n0.right = n1;
		int result = s.minDepth(n0);
		assertTrue("result = " + result, result == 2);
	}
}

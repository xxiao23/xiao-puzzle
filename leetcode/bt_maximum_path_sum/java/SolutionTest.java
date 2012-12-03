import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testMaxPathSum() {
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(6);
		TreeNode node3 = new TreeNode(-3);
		TreeNode node4 = new TreeNode(-6);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(2);
		TreeNode node7 = new TreeNode(-6);
		TreeNode node8 = new TreeNode(-6);
		TreeNode node9 = new TreeNode(-6);
		node1.left = node2;
		node1.right = node3;
		node3.left = node4;
		node3.right = node5;
		node5.left = node6;
		node6.left = node7;
		node6.right = node8;
		node7.left = node9;
		Solution s = new Solution();
		int result = s.maxPathSum(node1);
		System.out.println(result);
		assertEquals(16, result);
	}

}

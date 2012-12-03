import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SolutionTest {

	@Test
	public void testLevelOrder() {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		node1.right = node2;
		node2.left = node3;
		node1.left = node4;
		node4.left = node5;
		node5.right = node6;
		Solution solution = new Solution();
		ArrayList<ArrayList<Integer>> result = solution.levelOrder(node1); 
		System.out.println(result);
		assertTrue(result.size() > 0);
	}

}

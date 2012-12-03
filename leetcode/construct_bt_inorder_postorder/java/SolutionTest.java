import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testBuildTree() {
//		TreeNode[] nodes = new TreeNode[8];
//		for (int i = 1; i <= 8; ++i) {
//			nodes[i] = new TreeNode(i);
//		}
//		nodes[0].left = nodes[1];
//		nodes[0].right = nodes[2];
//		nodes[1].left = nodes[3];
//		nodes[1].right = nodes[4];
//		nodes[2].left = nodes[5];
//		nodes[2].right = nodes[6];
//		nodes[5].right = nodes[7];
		
		int[] inorder = {4,2,5,1,6,8,3,7};
		int[] postorder = {4,5,2,8,6,7,3,1};
		Solution solution = new Solution();
		TreeNode root = solution.buildTree(inorder, postorder);
		System.out.println(root);
		assertNotNull(root);
	}
	
	@Test
	public void testBuildTree1() {
		int[] inorder = {};
		int[] postorder = {};
		Solution solution = new Solution();
		TreeNode root = solution.buildTree(inorder, postorder);
		System.out.println(root);
		assertNull(root);
	}

	@Test
	public void testBuildTree2() {
		int[] inorder = {2,1};
		int[] postorder = {2,1};
		Solution solution = new Solution();
		TreeNode root = solution.buildTree(inorder, postorder);
		System.out.println("root = " + root.val);
		assertNotNull(root);
	}
}

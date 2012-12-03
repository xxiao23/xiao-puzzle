import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testBuildTree() {
		int[] preorder = {1,2,3};
		int[] inorder = {2,3,1};
		Solution solution = new Solution();
		TreeNode root = solution.buildTree(preorder, inorder);
		System.out.println(root.val);
		assertNotNull(root);
	}

}

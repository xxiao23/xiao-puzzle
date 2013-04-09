import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		TreeNode[] nodes = new TreeNode[7];
		nodes[0] = new TreeNode(1);
		nodes[1] = new TreeNode(2);
		nodes[2] = new TreeNode(2);
		nodes[3] = new TreeNode(3);
		nodes[4] = new TreeNode(4);
		nodes[5] = new TreeNode(4);
		nodes[6] = new TreeNode(3);
		
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		nodes[1].left = nodes[3];
		nodes[1].right =nodes[4];
		nodes[2].left = nodes[5];
		nodes[2].right = nodes[6];
		
		boolean result = s.isSymmetric(nodes[0]);
		assertTrue(result);
	}
	
	@Test
	public void test2() {
		TreeNode[] nodes = new TreeNode[5];
		nodes[0] = new TreeNode(1);
		nodes[1] = new TreeNode(2);
		nodes[2] = new TreeNode(2);
		nodes[3] = new TreeNode(3);
		nodes[4] = new TreeNode(3);
		
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		nodes[1].right =nodes[3];
		nodes[2].right = nodes[4];
		
		boolean result = s.isSymmetric(nodes[0]);
		assertFalse(result);
	}
	
	@Test
	public void test3() {
		boolean result = s.isSymmetric(null);
		assertTrue(result);
		
		TreeNode[] nodes = new TreeNode[2];
		nodes[0] = new TreeNode(0);
		nodes[1] = new TreeNode(1);
		nodes[0].left = nodes[1];
		result = s.isSymmetric(nodes[1]);
		assertTrue(result);
		result = s.isSymmetric(nodes[0]);
		assertFalse(result);
	}
}

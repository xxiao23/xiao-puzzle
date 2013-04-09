import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		TreeNode[] nodes = new TreeNode[3];
		for (int i = 0; i < 3; i++) {
			nodes[i] = new TreeNode(i+1);
		}
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		int result = s.sumNumbers(nodes[0]);
		assertTrue(Integer.valueOf(result).toString(), result == 25);
	}
	
	@Test 
	public void test2() {
		TreeNode root = new TreeNode(0);
		int result = s.sumNumbers(root);
		assertTrue(Integer.valueOf(result).toString(), result == 0);
	}
}

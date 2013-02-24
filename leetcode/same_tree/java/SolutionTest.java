import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		TreeNode[] ns = new TreeNode[3];
		TreeNode[] ns2 = new TreeNode[3];
		for (int i = 0; i < 3; ++i) {
			ns[i] = new TreeNode(i+1);
			ns2[i] = new TreeNode(i+1);
		}
		ns[0].left = ns[1];
		ns[0].right = ns[2];
		ns2[0].left = ns2[1];
		ns2[0].right = ns2[2];
		
		boolean result = s.isSameTree(ns[0], ns2[0]);
		assertTrue(result);
	}
	
	@Test
	public void test2() {
		TreeNode[] ns = new TreeNode[3];
		TreeNode[] ns2 = new TreeNode[3];
		for (int i = 0; i < 3; ++i) {
			ns[i] = new TreeNode(i+1);
			ns2[i] = new TreeNode(i+1);
		}
		ns[0].left = ns[1];
		ns[0].right = ns[2];
		ns2[0].left = ns2[1];
		ns2[1].left = ns2[2];
		
		boolean result = s.isSameTree(ns[0], ns2[0]);
		assertFalse(result);
	}

}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		
		// test 1
		TreeNode[] ns = new TreeNode[7];
		ns[0] = new TreeNode(4);
		ns[1] = new TreeNode(5);
		ns[2] = new TreeNode(6);
		ns[3] = new TreeNode(1);
		ns[4] = new TreeNode(3);
		ns[5] = new TreeNode(2);
		ns[6] = new TreeNode(7);
		
		ns[0].left = ns[1];
		ns[0].right = ns[2];
		ns[1].left = ns[3];
		ns[1].right = ns[4];
		ns[2].left = ns[5];
		ns[2].right = ns[6];
		
		s.recoverTree(ns[0]);
		assertTrue(ns[1].val == 2);
		assertTrue(ns[5].val == 5);
		
		// test 2
		TreeNode[] ns1 = new TreeNode[1];
		ns1[0] = new TreeNode(1);
		s.recoverTree(ns1[0]);
		assertTrue(ns1[0].val == 1);
		
		// test 3
		TreeNode[] ns2 = new TreeNode[2];
		ns2[0] = new TreeNode(1);
		ns2[1] = new TreeNode(2);
		ns2[0].left = ns2[1];
		s.recoverTree(ns2[0]);
		assertTrue("root = " + ns2[0], ns2[0].val == 2);
		assertTrue("left = " + ns2[1], ns2[1].val == 1);
	}

}

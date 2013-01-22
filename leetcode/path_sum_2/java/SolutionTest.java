import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testPathSum1() {
		TreeNode[] nodes = new TreeNode[10];
		nodes[0] = new TreeNode(5);
		nodes[1] = new TreeNode(4);
		nodes[2] = new TreeNode(8);
		nodes[3] = new TreeNode(11);
		nodes[4] = new TreeNode(13);
		nodes[5] = new TreeNode(4);
		nodes[6] = new TreeNode(7);
		nodes[7] = new TreeNode(2);
		nodes[8] = new TreeNode(5);
		nodes[9] = new TreeNode(1);
		
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[2];
		nodes[1].left = nodes[3];
		nodes[2].left = nodes[4];
		nodes[2].right = nodes[5];
		nodes[3].left = nodes[6];
		nodes[3].right = nodes[7];
		nodes[5].left = nodes[8];
		nodes[5].right = nodes[9];
		
		ArrayList<ArrayList<Integer>> result = s.pathSum(nodes[0], 22);
		System.out.println(result);
		assertTrue("no. of solutions = " + result.size(), result.size() == 2);
	}

}

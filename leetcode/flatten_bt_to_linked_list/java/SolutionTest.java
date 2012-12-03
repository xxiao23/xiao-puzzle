import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testFlatten() {
		TreeNode[] nodes = new TreeNode[6];
		for (int i = 0; i < 6; ++i) {
			nodes[i] = new TreeNode(i+1);
		}
		nodes[0].left = nodes[1];
		nodes[0].right = nodes[4];
		nodes[1].left = nodes[2];
		nodes[1].right = nodes[3];
		nodes[4].right = nodes[5];
		Solution solution = new Solution();
		solution.flatten(nodes[0]);
		TreeNode cur = nodes[0];
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.right;
		}
		assertTrue(nodes[0] != null);
	}

}

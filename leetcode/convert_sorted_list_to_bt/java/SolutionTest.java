import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testSortedListToBST() {
		ListNode[] nodes = new ListNode[5];
		for(int i = 0; i < 5; ++i) {
			nodes[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 4; ++i) {
			nodes[i].next = nodes[i+1];
		}
		Solution solution = new Solution();
		TreeNode root = solution.sortedListToBST(nodes[0]);
		System.out.println(root.val);
		assertNotNull(root);
	}

}

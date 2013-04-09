import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		ListNode[] nodes = new ListNode[4];
		for (int i = 0; i < 4; i++) {
			nodes[i] = new ListNode(i+1);
			if (i > 0) {
				nodes[i-1].next = nodes[i];
			}
		}
		ListNode nHead = s.swapPairs(nodes[0]);
		assertTrue(Integer.valueOf(nHead.val).toString(), nHead.val == 2);
	}

	@Test
	public void test2() {
		ListNode[] nodes = new ListNode[1];
		for (int i = 0; i < 1; i++) {
			nodes[i] = new ListNode(i+1);
			if (i > 0) {
				nodes[i-1].next = nodes[i];
			}
		}
		ListNode nHead = s.swapPairs(nodes[0]);
		assertTrue(Integer.valueOf(nHead.val).toString(), nHead.val == 1);
	}
}

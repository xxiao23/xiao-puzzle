import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testPartition1() {
		ListNode[] l = new ListNode[6];
		l[0] = new ListNode(1);
		l[1] = new ListNode(4);
		l[2] = new ListNode(3);
		l[3] = new ListNode(2);
		l[4] = new ListNode(5);
		l[5] = new ListNode(2);
		
		for(int i = 0; i < 5; ++i) {
			l[i].next = l[i+1];
		}
		
		ListNode result = s.partition(l[0], 3);
		assertTrue("head = " + result.val, result.val == 1);
		
		
	}
	
	@Test
	public void testPartition2() {
		ListNode[] l = new ListNode[6];
		l[0] = new ListNode(4);
		l[1] = new ListNode(4);
		l[2] = new ListNode(3);
		l[3] = new ListNode(2);
		l[4] = new ListNode(5);
		l[5] = new ListNode(2);
		
		for(int i = 0; i < 5; ++i) {
			l[i].next = l[i+1];
		}
		
		ListNode result = s.partition(l[0], 3);
		assertTrue("head = " + result.val, result.val == 2);
		
		
	}
}

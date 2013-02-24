import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		ListNode[] ns = new ListNode[3];
		for (int i = 0; i < 3; ++i) {
			ns[i] = new ListNode(i);
		}
		for (int i = 0; i < 2; ++i) {
			ns[i].next = ns[i+1];
		}
		// remove sth in the middle
		ListNode nth = s.removeNthFromEnd(ns[0], 2);
		assertTrue(nth != null && nth.val == 0);
		assertTrue(nth.next != null && nth.next.val == 2);
	}

	@Test
	public void test2() {
		ListNode[] ns = new ListNode[3];
		for (int i = 0; i < 3; ++i) {
			ns[i] = new ListNode(i);
		}
		for (int i = 0; i < 2; ++i) {
			ns[i].next = ns[i+1];
		}
		// remove the head
		ListNode nth = s.removeNthFromEnd(ns[0], 3);
		assertTrue(nth != null && nth.val == 1);
		assertTrue(nth.next != null && nth.next.val == 2);
	}
	
	@Test
	public void test3() {
		ListNode[] ns = new ListNode[3];
		for (int i = 0; i < 3; ++i) {
			ns[i] = new ListNode(i);
		}
		for (int i = 0; i < 2; ++i) {
			ns[i].next = ns[i+1];
		}
		// remove the tail
		ListNode nth = s.removeNthFromEnd(ns[0], 1);
		assertTrue(nth != null && nth.val == 0);
		assertTrue(nth.next != null && nth.next.val == 1);
	}
	
	@Test
	public void test4() {
		ListNode n = new ListNode(0);
		// single node list
		ListNode nth = s.removeNthFromEnd(n, 1);
		assertNull(nth);
	}
}

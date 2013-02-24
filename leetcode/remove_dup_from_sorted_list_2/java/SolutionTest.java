import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	@Test
	public void test1() {
		ListNode n0 = new ListNode(1);
		ListNode head = s.deleteDuplicates(n0);
		assertNotNull(head);
		assertTrue("head = " + head, head.val == 1);
	}
	
	@Test
	public void test2() {
		ListNode[] ns = new ListNode[2];
		ns[0] = new ListNode(1);
		ns[1] = new ListNode(2);
		ns[0].next = ns[1];
		ListNode head = s.deleteDuplicates(ns[0]);
		assertNotNull(head);
		assertTrue(head.val == 1);
	}
	
	@Test
	public void test3() {
		ListNode[] ns = new ListNode[2];
		ns[0] = new ListNode(1);
		ns[1] = new ListNode(1);
		ns[0].next = ns[1];
		ListNode head = s.deleteDuplicates(ns[0]);
		assertNull(head);
	}
	
	@Test
	public void test4() {
		ListNode[] ns = new ListNode[3];
		ns[0] = new ListNode(1);
		ns[1] = new ListNode(1);
		ns[2] = new ListNode(2);
		ns[0].next = ns[1];
		ns[1].next = ns[2];
		ListNode head = s.deleteDuplicates(ns[0]);
		assertNotNull(head);
		assertTrue(head.val == 2);
	}
}

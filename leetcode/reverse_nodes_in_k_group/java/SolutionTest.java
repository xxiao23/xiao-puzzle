import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		ListNode[] ns = new ListNode[5];
		for (int i = 0; i < 5; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 4; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseKGroup(ns[0], 2);
		assertNotNull(result);
		assertTrue(result.val == 2);
		assertNotNull(result.next);
		assertTrue(result.next.val == 1);
	}
	
	@Test
	public void test2() {
		ListNode[] ns = new ListNode[5];
		for (int i = 0; i < 5; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 4; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseKGroup(ns[0], 3);
		assertNotNull(result);
		assertTrue(result.val == 3);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
	
	@Test
	public void test3() {
		ListNode[] ns = new ListNode[5];
		for (int i = 0; i < 5; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 4; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseKGroup(ns[0], 5);
		assertNotNull(result);
		assertTrue(result.val == 5);
		assertNotNull(result.next);
		assertTrue(result.next.val == 4);
	}
	
	@Test
	public void test4() {
		ListNode[] ns = new ListNode[5];
		for (int i = 0; i < 5; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 4; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseKGroup(ns[0], 1);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
	
	@Test
	public void test5() {
		ListNode[] ns = new ListNode[5];
		for (int i = 0; i < 5; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 4; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseKGroup(ns[0], 6);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
	

}

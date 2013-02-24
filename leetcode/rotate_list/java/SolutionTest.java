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
		ListNode result = s.rotateRight(ns[0], 2);
		assertNotNull(result);
		assertTrue(result.val == 4);
		assertNotNull(result.next);
		assertTrue(result.next.val == 5);
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
		ListNode result = s.rotateRight(ns[0], 0);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
	
	@Test
	public void test3() {
		ListNode result = s.rotateRight(null, 2);
		assertNull(result);
	}
	
	@Test
	public void test4() {
		ListNode[] ns = new ListNode[2];
		for (int i = 0; i < 2; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 1; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.rotateRight(ns[0], 5);
		assertNotNull(result);
		assertTrue(result.val == 2);
		assertNotNull(result.next);
		assertTrue(result.next.val == 1);
	}
	
	@Test
	public void test5() {
		ListNode[] ns = new ListNode[2];
		for (int i = 0; i < 2; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 1; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.rotateRight(ns[0], 4);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
	
	@Test
	public void test6() {
		ListNode[] ns = new ListNode[2];
		for (int i = 0; i < 2; ++i) {
			ns[i] = new ListNode(i+1);
		}
		for (int i = 0; i < 1; ++i) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.rotateRight(ns[0], 2);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		ListNode[] ns = new ListNode[5];
		for (int i = 1; i <= 5; i++) {
			ns[i-1] = new ListNode(i);
		}
		for (int i = 0; i < 4; i++) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseBetween(ns[0], 2, 4);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 4);
	}
	
	@Test
	public void test2() {
		ListNode[] ns = new ListNode[5];
		for (int i = 1; i <= 5; i++) {
			ns[i-1] = new ListNode(i);
		}
		for (int i = 0; i < 4; i++) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseBetween(ns[0], 2, 2);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 2);
	}
	
	@Test
	public void test3() {
		ListNode[] ns = new ListNode[5];
		for (int i = 1; i <= 5; i++) {
			ns[i-1] = new ListNode(i);
		}
		for (int i = 0; i < 4; i++) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseBetween(ns[0], 2, 5);
		assertNotNull(result);
		assertTrue(result.val == 1);
		assertNotNull(result.next);
		assertTrue(result.next.val == 5);
	}
	
	@Test
	public void test4() {
		ListNode[] ns = new ListNode[5];
		for (int i = 1; i <= 5; i++) {
			ns[i-1] = new ListNode(i);
		}
		for (int i = 0; i < 4; i++) {
			ns[i].next = ns[i+1];
		}
		ListNode result = s.reverseBetween(ns[0], 1, 5);
		assertNotNull(result);
		assertTrue(result.val == 5);
		assertNotNull(result.next);
		assertTrue(result.next.val == 4);
	}
}

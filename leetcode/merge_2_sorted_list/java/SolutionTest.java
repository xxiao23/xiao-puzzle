import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMergeTwoLists1() {
		ListNode[] ns = new ListNode[7];
		for(int i = 0; i < 7; ++i) {
			ns[i] = new ListNode(i);
		}
		ns[0].next = ns[2];
		ns[2].next = ns[4];
		ns[4].next = ns[6];
		ns[1].next = ns[3];
		ns[3].next = ns[5];
		
		ListNode result = s.mergeTwoLists(ns[0], ns[1]);
		System.out.println(result);
		assertTrue(result.val == 0);
	}
	
	@Test
	public void testMergeTwoLists2() {
		ListNode result = s.mergeTwoLists(null, null);
		System.out.println(result);
		assertNull(result);
	}

}

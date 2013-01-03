import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testMergeKLists1() {
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		ListNode[] arr = new ListNode[6];
		for (int i = 0; i < 6; ++i) {
			arr[i] = new ListNode(i);
		}
		arr[0].next = arr[3];
		arr[1].next = arr[4];
		arr[2].next = arr[5];
		lists.add(arr[0]);
		lists.add(arr[1]);
		lists.add(arr[2]);
		
		ListNode result = s.mergeKLists(lists);
		assertNotNull(result);
		assertTrue("result.val = " + result.val, result.val == 0);
	}
	
	@Test
	public void testMergeKLists2() {
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		ListNode result = s.mergeKLists(lists);
		assertNull(result);
	}
}

import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testAddTwoNumbers() {
		ListNode l11 = new ListNode(2);
		ListNode l12 = new ListNode(4);
		ListNode l13 = new ListNode(3);
		l11.next = l12;
		l12.next = l13;
		
		ListNode l21 = new ListNode(5);
		ListNode l22 = new ListNode(6);
		ListNode l23 = new ListNode(4);
		l21.next = l22;
		l22.next = l23;
		
		ListNode result = new Solution().addTwoNumbers(l11, l21);
		assertNotNull(result);
		ListNode it = result;
		while (it != null) {
			System.out.println(it.val);
			it = it.next;
		}
	}

}

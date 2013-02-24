
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (head == null) {
        	return null;
        }
        
        ListNode pHead = new ListNode(head.val-1);
        ListNode lastDistinct = pHead;
        pHead.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        int dupCount = 1;
        while (cur != null) {
        	if (prev.val == cur.val) {
        		++dupCount;
        	} else {
        		if (dupCount == 1) {
        			lastDistinct.next = prev;
        			lastDistinct = prev;
        		} else {
        			lastDistinct.next = cur;
        		}
        		dupCount = 1;
        	}
        	prev = cur;
    		cur = cur.next;
        }
        
        if (dupCount > 1) {
        	lastDistinct.next = null;
        }
        
        return pHead.next;
    }
}

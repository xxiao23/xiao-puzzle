
public class Solution {
    public ListNode swapPairs(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ListNode pHead = new ListNode(0);
    	pHead.next = head;
    	ListNode prev = pHead;
    	ListNode cur1 = head;
    	ListNode cur2 = null;
    	if (cur1 != null) {
    		cur2 = cur1.next;
    	}
    	
    	while (cur1 != null && cur2 != null) {
    		cur1.next = cur2.next;
    		cur2.next = cur1;
    		prev.next = cur2;
    		
    		prev = cur1;
    		cur1 = cur1.next;
    		cur2 = null;
    		if (cur1 != null) {
    			cur2 = cur1.next;
    		}
    	}
    	
    	return pHead.next;
    }
}

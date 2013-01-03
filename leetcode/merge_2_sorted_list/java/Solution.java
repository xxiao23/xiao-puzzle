
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ListNode h = new ListNode(0);
    	ListNode prev = h;
    	ListNode i1 = l1;
    	ListNode i2 = l2;
    	
    	while (i1 != null & i2 != null) {
    		if (i1.val <= i2.val) {
    			prev.next = i1;
    			prev = i1;
    			i1 = i1.next;
    		} else {
    			prev.next = i2;
    			prev = i2;
    			i2 = i2.next;
    		}
    	}
    	
    	if (i1 != null) {
    		prev.next = i1;
    	}
    	
    	if (i2 != null) {
    		prev.next = i2;
    	}
    	
        return h.next;
    }
}

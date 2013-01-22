
public class Solution {
    public ListNode partition(ListNode head, int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	ListNode ret = new ListNode(x-1);
    	ListNode lastLp = ret; // point to the last element < x
    	
    	ListNode prev = lastLp;
    	prev.next = head;
    	ListNode cur = head;
    	while (cur != null) {
    		if (cur.val >= x || lastLp.next == cur) {
    			if (cur.val < x) {
    				lastLp = cur;
    			}
    			prev = cur;
    			cur = cur.next;
    		} else {
    			prev.next = cur.next;
    			cur.next = lastLp.next;
    			lastLp.next = cur;
    			lastLp = cur;
    			cur = prev.next;
    		}
    	}
        return ret.next;
    }
}

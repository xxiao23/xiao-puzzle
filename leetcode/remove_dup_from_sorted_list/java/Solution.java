
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (head == null) {
            return null;
        }
        
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
        	if (prev.val != cur.val) {
                prev = cur;
        		cur = cur.next;
        	} else {
        		cur = cur.next;
        		prev.next = cur;
        	}
        }
        
        return head;
    }
}

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (head == null) {
        	return null;
        }
    	
    	ListNode tail = head;
    	int i = 0;
    	for (; i < n && tail != null; ++i) {
    		tail = tail.next;
    	}
    	
    	if (tail == null && i < n) {
    		return null;
    	}
    	
    	ListNode nth = head;
    	ListNode pHead = new ListNode(0);
    	pHead.next = head;
    	ListNode prev = pHead;
    	while (tail != null) {
    		prev = nth;
    		nth = nth.next;
    		tail = tail.next;
    	}
    	
    	// remove nth from end
    	prev.next = nth.next;
    	
    	return pHead.next;
    	
    }
}

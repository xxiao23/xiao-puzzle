
public class Solution {
	 public ListNode rotateRight(ListNode head, int n) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
		 	if (head == null) {
		 		return null;
		 	}
		 	if (n == 0) {
		 		return head;
		 	}
		 	
	        ListNode pHead = new ListNode(0);
	        pHead.next = head;
	        
	        ListNode tail = pHead;
	        int i = 0;
	        for (; tail.next != null && i < n; ++i ) {
	        	tail = tail.next;
	        }
	        
	        int k;
	        if (i < n) {
	        	// the length of the list is less than n
	        	k = n % i;
	        	tail = pHead;
	        	for (int j = 0; j < k; ++j) {
	        		tail = tail.next;
	        	}
	        } else if (tail.next == null) {
	        	// the length of the list is exactly n
	        	k = 0;
	        } else {
	        	// the length of the list is more than n
	        	k = n;
	        }
	        
	        if (k == 0) {
	        	return pHead.next;
	        }
	        
	        // identify the head and the tail
	        // of the last k nodes
	        ListNode kthToTail = pHead;
	        while (tail.next != null) {
	        	tail = tail.next;
	        	kthToTail = kthToTail.next;
	        }
	        
	        // reconnect the next pointers
	        tail.next = head;
	        pHead.next = kthToTail.next;
	        kthToTail.next = null;
	        
	        return pHead.next;
	    }
}

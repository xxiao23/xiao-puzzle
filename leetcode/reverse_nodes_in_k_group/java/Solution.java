
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
        ListNode pHead = new ListNode(0);
        pHead.next = head;
        ListNode prev = pHead;
        while (prev != null) {
        	prev = reverseBetween(prev, k);
        }
        
        return pHead.next;
    }
    
    // return the tail
    // of the reversed list
    private ListNode reverseBetween(ListNode pHead, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ListNode head = pHead.next;
        if (head == null) {
        	return null;
        }
        
    	ListNode cn = head;
        for (int i = 0; i < n; ++i) {
        	if (cn == null) {
        		return null;
        	}
        	cn = cn.next;
        }
        
        cn = head;
    	ListNode pn = pHead;
    	ListNode nn = cn.next;
    	for (int i = 1; i < n && nn != null; ++i) {
    		pn = cn;
    		cn = nn;
    		nn = nn.next;
    		cn.next = pn; // reverse link
    	}
    	// link pHead to tail
    	pHead.next = cn;
    	// link the next of mth node
    	head.next = nn;
    	
    	return head;
    }
}


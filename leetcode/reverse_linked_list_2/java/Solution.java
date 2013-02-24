
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (head == null) {
        	return null;
        }
        
    	ListNode pHead = new ListNode(0);
    	pHead.next = head;
    	ListNode cn = pHead;
    	ListNode pn = null;
    	ListNode nn = null;
    	int i = 1;
    	for (; cn != null && i<= m; ++i) {
    		pn = cn;
    		cn = cn.next;
    		nn = cn.next;
    	}
    	ListNode mn = cn; // mth node
    	ListNode mpn = pn; // prev to mth node
    	for (; i <= n && cn != null; ++i) {
    		pn = cn;
    		cn = nn;
    		nn = nn.next;
    		cn.next = pn; // reverse link
    	}
    	// link nth node to prev of mth
    	mpn.next = cn;
    	// link the next of mth node
    	mn.next = nn;
    	
    	return pHead.next;
    }
}

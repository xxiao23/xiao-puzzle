/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode pHead = new ListNode(0);
        pHead.next = head;
        ListNode prevTail = pHead;
        ListNode rHead = head;
        ListNode rTail = head;
        int n = 0;
        while (rTail != null ) {
            n++;
            if (n == k) {
                n = 0;
                ListNode nextHead = rTail.next;
                reverseGroup(rHead, rTail); // rHead and rTail change after this
                prevTail.next = rTail;
                rHead.next = nextHead;
                prevTail = rHead;
                rHead = nextHead;
                rTail = nextHead;
            } else {
                rTail = rTail.next;
            }
        }
        
        return pHead.next;
    }
    
    private void reverseGroup(ListNode head, ListNode tail) {
        if (head == null || tail == null) return;
        if (head != tail) {
            reverseGroup(head.next, tail);
            head.next.next = head;
        }
    }
}

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // use two pointers n steps apart to locate the node to remove
        ListNode ph = new ListNode(0);
        ph.next = head;
        ListNode p1 = head; // pointer to traverse the list
        ListNode p2 = head; // pointer to locate the one to remove
        ListNode pp2 = ph; // previous pointer to p2
        int i = n;
        while (p1 != null) {
            p1 = p1.next;
            if (i < 1) {
                pp2 = p2;
                p2 = p2.next;
            }
            i--;
        }
        // remove p2 from the list
        pp2.next = p2.next;
        
        return ph.next;
    }
}

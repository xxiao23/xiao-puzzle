/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        if (head == null) return;
        int n = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            n++;
        }
        
        int k = 0;
        if (n%2 == 0) {
            k = n/2 + 1;
        } else {
            k = n/2 + 2;
        }
        
        // reverse the list from kth node onward
        
        // move the pointer to the begining of the part to be reversed
        ListNode ph = null, mid = null;
        p = head;
        while (k > 1) {
            k--;
            mid = p;
            p = p.next;
        }
        // reverse 1 node at a time
        while (p != null) {
            ListNode t = p.next;
            p.next = ph;
            ph = p;
            p = t;
        }
        mid.next = ph;
        
        // now pair up nodes by moving head and reversed head simultanenously
        ListNode p1 = head, p2 = ph;
        while (p2 != null) {
            ListNode p1n = p1.next;
            ListNode p2n = p2.next;
            p1.next = p2; // wire p1 to p2
            if (p1n != p2) p2.next = p1n;
            if (p1 != mid) mid.next = p2n;
            p1 = p1n;
            p2 = p2n;
        }
    }
}

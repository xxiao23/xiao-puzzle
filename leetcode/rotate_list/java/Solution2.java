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
    public ListNode rotateRight(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (n == 0) return head;
        if (head == null) return head;
        
        // search for the last node
        ListNode cur = head;
        int L = 1;
        while(cur.next != null) {
            cur = cur.next;
            ++L;
        }
        // wire the last node to the head
        ListNode newTail = cur;
        ListNode newHead = head;
        newTail.next = newHead;
        n = n % L;
        n = L - n;
        // move k steps from the head
        for(int i = 0; i < n; ++i) {
            newHead = newHead.next;
            newTail = newTail.next;
        }
        newTail.next = null;
        return newHead;
    }
}

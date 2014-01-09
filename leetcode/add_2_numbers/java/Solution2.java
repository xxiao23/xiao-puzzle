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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return solver(l1, l2, 0);
    }
    
    private ListNode solver(ListNode l1, ListNode l2, int c) {
        // base cases
        if (l1 == null && l2 == null) {
            if (c > 0) return new ListNode(c);
            else return null;
        }
        if (l1 == null) l1 = new ListNode(0);
        if (l2 == null) l2 = new ListNode(0);
        
        int k = l1.val + l2.val + c;
        int co = 0;
        if (k > 9) {
            co = 1;
            k -= 10;
        }
        ListNode node = new ListNode(k);
        ListNode nextNode = solver(l1.next, l2.next, co);
        node.next = nextNode;
        return node;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        if (head == null) return null;
        
        // use racing pointers to find the split point
        ListNode psp = null; // predecessor to slow pointer
        ListNode sp = head; // slow pointer 
        ListNode fp = head; // fast pointer
        while (fp != null) {
            fp = fp.next;
            if (fp == null) break;
            fp = fp.next;
            psp = sp;
            sp = sp.next;
        }
        
        // break the list into left, root, and right
        // recursively build up left and right
        TreeNode root = new TreeNode(sp.val);
        if (head != sp) {
            psp.next = null;
            root.left = sortedListToBST(head);
        }
        if (sp.next != null) {
            root.right = sortedListToBST(sp.next);
        }
        
        return root;
    }
}

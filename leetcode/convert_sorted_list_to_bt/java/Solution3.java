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
    private ListNode l = null;
    public TreeNode sortedListToBST(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int n = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            n++;
        }
        this.l = head;
        return worker(0, n-1);
    }
    
    private TreeNode worker(int start, int end) {
        if (start > end) {
            return null;
        }
        
        int mid = (start+end)/2;
        TreeNode lc = worker(start, mid-1);
        TreeNode root = new TreeNode(this.l.val);
        this.l = this.l.next;
        TreeNode rc = worker(mid+1, end);
        root.left = lc;
        root.right = rc;
        return root;
    }
}

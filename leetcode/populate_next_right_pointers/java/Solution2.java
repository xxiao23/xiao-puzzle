/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        TreeLinkNode curLevelHead = root;
        TreeLinkNode prevLevelHead = null;
        while (curLevelHead != null) {
            TreeLinkNode prev = prevLevelHead;
            while (prev != null) {
                prev.left.next = prev.right;
                if (prev.next == null) prev.right.next = null;
                else prev.right.next = prev.next.left;
                prev = prev.next;
            }
            
            prevLevelHead = curLevelHead;
            curLevelHead = curLevelHead.left;
        }
    }
    
}

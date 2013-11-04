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
        if (root == null) return;
        // initialization
        root.next = null;
        TreeLinkNode lh = root; // level head
        while (lh != null) {
            TreeLinkNode pl = lh;
            while (pl != null && pl.left == null && pl.right == null) {
                pl = pl.next;
            }
            // figure out the head of next level
            if (pl != null) {
                if (pl.left != null) lh = pl.left;
                else lh = pl.right;
            } else {
                lh = null;
            }
            // use 2 nodes from prev level
            // each has at least 1 child
            // and connect the children
            TreeLinkNode pln = null;
            while (pl != null) {
                pln = pl.next;
                while (pln != null && pln.left == null & pln.right == null) {
                    pln = pln.next;
                }
                // now 
                // pl and pln each has at least 1 child
                TreeLinkNode plLast = null;
                if (pl.right != null) {
                    // has right
                    if (pl.left != null) {
                        // has left too
                        pl.left.next = pl.right;
                    }
                    plLast = pl.right;
                } else {
                    // must have left only
                    plLast = pl.left;
                }
                
                if (pln == null) {
                    plLast.next = null;
                } else {
                    if (pln.left != null) plLast.next = pln.left;
                    else plLast.next = pln.right;
                }
                
                // move to the next
                pl = pln;
            }
        }
    }
}

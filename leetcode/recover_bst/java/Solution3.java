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
    public void recoverTree(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        TreeNode first = null, second = null; // two nodes to be reversed
        TreeNode last = null; // prev node in the inorder sequence
        // Morris inorder traversal
        // O(1) space
        // O(nlgn) runtime
        TreeNode cur = root; // current node
        while (cur != null) {
            if (cur.left == null) {
                // left child is empty
                
                // output the current node
                // set current to the right child
                if (last != null && last.val > cur.val) {
                    if (first == null) {
                        first = last;
                        second = cur;
                    } else {
                        second = cur;
                    }
                }
                last = cur;
                cur = cur.right;
            } else {
                // left child is not empty
                
                // find the predecessor of current node
                TreeNode predecessor = cur.left;
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                
                if (predecessor.right == null) {
                    // if predecessor's right child is empty
                    // make current node its right child
                    predecessor.right = cur;
                    // move to current's left
                    cur = cur.left;
                } else {
                    // output the cur node
                    if (last != null && last.val > cur.val) {
                        if (first == null) {
                            first = last;
                            second = cur;
                        } else {
                            second = cur;
                        }
                    }
                    last = cur;
                    cur = cur.right;
                    // restore predecessor's right child to null
                    predecessor.right = null;
                }
            }  
        }
        
        if (first != null) {
            // swap first and second
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }
}

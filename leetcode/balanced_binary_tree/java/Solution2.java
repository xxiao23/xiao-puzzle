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
    public boolean isBalanced(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        return worker(root) != -1;
    }
    
    // return the depth of the tree
    // return -1 if the tree is unbalanced
    private int worker(TreeNode root) {
        if (root == null) return 0;
        int ld = worker(root.left);
        if (ld == -1) return -1;
        int rd = worker(root.right);
        if (rd == -1) return -1;
        if (Math.abs(ld-rd) > 1) return -1;
        return 1+Math.max(ld, rd);
    }
}

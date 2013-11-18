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
    public boolean isValidBST(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) return true;
        return worker(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public boolean worker(TreeNode root, int min, int max) {
        if (root.val <= min || root.val >= max) return false;
        
        if (root.left != null && !worker(root.left, min, root.val)) return false;
        if (root.right != null && !worker(root.right, root.val, max)) return false;
        
        return true;
    }
}

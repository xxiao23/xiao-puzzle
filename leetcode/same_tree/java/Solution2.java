/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution2 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        
        boolean ret = true;
        ret = ret && (p.val == q.val);
        ret = ret && isSameTree(p.left, q.left);
        ret = ret && isSameTree(p.right, q.right);
        
        return ret;
    }
}

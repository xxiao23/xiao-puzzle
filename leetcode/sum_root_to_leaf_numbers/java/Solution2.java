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
    public int sumNumbers(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        return worker(root, 0);
    }
    
    private int worker(TreeNode root, int p) {
        if (root == null) return p;
        if (root.left == null && root.right == null) return root.val+10*p;
        int sum = 0;
        if (root.left != null) {
            sum += worker(root.left, root.val+10*p);
        }
        if (root.right != null) {
            sum += worker(root.right, root.val+10*p);
        }
        return sum;
    }
}

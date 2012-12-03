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
	private boolean isBalancedTree = true;
	
    public boolean isBalanced(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	isBalancedTree = true;
    	treeHeight(root);
    	return isBalancedTree;
    }
    
    private int treeHeight(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	// non-empty tree
    	int leftHeight = treeHeight(root.left);
    	int rightHeight = treeHeight(root.right);
    	if (leftHeight - rightHeight > 1 || rightHeight - leftHeight > 1) {
    		isBalancedTree = false;
    	}
    	if (leftHeight > rightHeight) {
    		return 1 + leftHeight;
    	} else {
    		return 1 + rightHeight;
    	}
    }
}
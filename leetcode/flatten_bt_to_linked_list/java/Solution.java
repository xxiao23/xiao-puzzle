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
    public void flatten(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        flattenRecursive(root);
    }
    
    private TreeNode flattenRecursive(TreeNode root) {
    	if (root == null) {
    		return null;
    	}
    	
    	TreeNode leftTail = null;
    	TreeNode rightTail = null;
    	if (root.left != null) {
    		leftTail = flattenRecursive(root.left);
    	}
    	if (root.right != null) {
    		rightTail = flattenRecursive(root.right);
    	}
    	
    	if (leftTail != null) {
    		leftTail.right = root.right;
    		root.right = root.left;
    		root.left = null;
    		if (rightTail != null) {
    			return rightTail;
    		} else {
    			return leftTail;
    		}
    	} else if (rightTail != null) {
    		return rightTail;
    	} else {
    		return root;
    	}
    	
    }
}
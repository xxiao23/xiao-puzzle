
public class Solution {
    public boolean isValidBST(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null) {
        	return true;
        }
        
        // check if left subtree is valid BST
        if (isValidBST(root.left) == false) {
        	return false;
        }
        
        // check if right subtree is valid BST
        if (isValidBST(root.right) == false) {
        	return false;
        }
        
        // get the largest in the left subtree
        if (root.left != null) {
        	TreeNode t = root.left;
        	while (t.right != null) {
        		t = t.right;
        	}
        	if (t.val >= root.val) {
        		return false;
        	}
        }
        
        // get the smallest in the right subtree
        if (root.right != null) {
        	TreeNode t = root.right;
        	while (t.left != null) {
        		t = t.left;
        	}
        	if (t.val <= root.val) {
        		return false;
        	}
        }
        
        return true;
    }
}

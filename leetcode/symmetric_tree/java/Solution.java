
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (root == null) {
    		return true;
    	}
    	
    	return isSymmetricRecursive(root.left, root.right);
    }
    
    private boolean isSymmetricRecursive(TreeNode p1, TreeNode p2) {
    	if (p1 == null && p2 == null) {
    		return true;
    	}
    	
    	if ((p1 == null && p2 != null) ||
    		(p1 != null && p2 == null) ||
    		(p1.val != p2.val)) {
    		return false;
    	}
    	
    	return isSymmetricRecursive(p1.left, p2.right) 
    		&& isSymmetricRecursive(p1.right, p2.left);
    
    }
}

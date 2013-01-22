
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (root == null) {
    		return false;
    	}
    	
        return hasPathSumRecursive(root, sum);
    }
    
    private boolean hasPathSumRecursive(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (root.left == null && root.right == null) {
    		if (sum == root.val) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	boolean result = false;
    	if (root.left != null) {
    		result = result || hasPathSumRecursive(root.left, sum-root.val);
    	}
    	if (root.right != null) {
    		result = result || hasPathSum(root.right, sum-root.val);
    	}
    	
    	return result;
    }
    
}

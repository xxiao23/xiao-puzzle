
public class Solution {
    public int maxDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (root == null) {
    		return 0;
    	}
    	
    	int ld = maxDepth(root.left);
    	int rd = maxDepth(root.right);
    	
        return 1 + Math.max(ld, rd);
    }
    
}

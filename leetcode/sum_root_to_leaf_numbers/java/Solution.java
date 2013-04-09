
public class Solution {
	private int result = 0;
	private int num = 0;
	
    public int sumNumbers(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        result = 0;
        if (root == null) {
        	return result;
        }
        
        num = 0;
        dfs(root);
        return result;
    }
    
    private void dfs(TreeNode root) {
    	this.num *= 10;
    	this.num += root.val;
    	
    	if (root.left == null && root.right == null) {
    		this.result += this.num;
    	} else {
	    	if (root.left != null) {
	    		dfs(root.left);
	    	}
	    	if (root.right != null) {
	    		dfs(root.right);
	    	}
    	}
    	
    	this.num /= 10;
    }
}

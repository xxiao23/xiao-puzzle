public class Solution {
	TreeNode cur = null;
	TreeNode prev = null;
	TreeNode n1 = null;
	TreeNode n2 = null;
	
    public void recoverTree(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        this.cur = null;
        this.prev = null;
        this.n1 = null;
        this.n2 = null;
        inorder(root);
        if (n1 != null && n2 != null) {
        	int tmp = n1.val;
        	n1.val = n2.val;
        	n2.val = tmp;
        }
    }
    
    private void inorder(TreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	inorder(root.left);
    	
    	prev = cur;
    	cur = root;
    	
    	if (prev != null  && cur != null) { 
    		if (prev.val > cur.val) {
    			if (n1 == null) {
    				n1 = prev;
    			} 
    			n2 = cur;
    		}
    	}
    	
    	inorder(root.right);
    }
}


public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	// the 2 trees are same,
    	// if and only if both their in-order and
    	// post-order traversal outputs are the same.
    	return sameInOrder(p, q) && samePostOrder(p, q);
    }
    
    private boolean sameInOrder(TreeNode p, TreeNode q) {
    	if (p == null && q == null) {
    		return true;
    	} else if (p == null && q != null) {
    		return false;
    	} else if (p != null && q == null) {
    		return false;
    	}
    	
    	return sameInOrder(p.left, q.left)
    		&& (p.val == q.val)
    		&& sameInOrder(p.right, q.right);
    		
    }
    
    private boolean samePostOrder(TreeNode p, TreeNode q) {
    	if (p == null && q == null) {
    		return true;
    	} else if (p == null && q != null) {
    		return false;
    	} else if (p != null && q == null) {
    		return false;
    	}
    	
    	return samePostOrder(p.left, q.left)
    		&& samePostOrder(p.right, q.right)
    		&& (p.val == q.val);

    }
}

import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    public int minDepth(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null) {
        	return 0;
        }
        
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        q.add(null);
        int d = 1;
        while(q.size() > 1) {
        	TreeNode tn = q.poll();
        	if (tn == null) {
        		q.add(null);
        		d += 1;
        	} else {
        		if (tn.left == null && tn.right == null) {
            		break;
            	}
        		if (tn.left != null) {
        			q.add(tn.left);
        		}
        		if (tn.right != null) {
        			q.add(tn.right);
        		}
        	}
        }
        
        return d;
        
    }
}

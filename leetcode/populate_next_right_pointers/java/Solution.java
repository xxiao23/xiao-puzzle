import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    public void connect(TreeLinkNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (root == null) {
    		return;
    	}
    	
    	// BFS-like
    	Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
    	q.add(root);
    	q.add(null);
    	while (q.size() > 1) {
    		TreeLinkNode n = q.remove();
    		if (n == null) {
    			q.add(null);
    		} else {
    			n.next = q.element();
    			if (n.left != null) {
    				q.add(n.left);
    			}
    			if (n.right != null) {
    				q.add(n.right);
    			}
    		}
    	}
    }
}

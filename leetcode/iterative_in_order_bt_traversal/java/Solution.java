import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

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
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
    	ArrayList<Integer> result = new ArrayList<Integer>();
        // Start typing your Java solution below
        // DO NOT write main() function
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        boolean stackGrowth = true;

        if (root != null) {
        	stack.addLast(root);
        	stackGrowth = true;
        }
        
        while (stack.size() > 0) {
        	// get the node on top of the stack
        	TreeNode curNode = stack.peekLast();
        	// go further left
        	if (stackGrowth && curNode.left != null) {
        		stack.addLast(curNode.left);
        	} else {
        		System.out.println(curNode.val);
        		// pop current node
        		result.add(curNode.val);
        		stack.removeLast();
        		stackGrowth = false;
        		// push in right
        		if (curNode.right != null) {
        			stack.addLast(curNode.right);
        			stackGrowth = true;
        		}
        	}
        }
        
        return result;
    }
}

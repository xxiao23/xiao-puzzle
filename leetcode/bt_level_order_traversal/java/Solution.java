import java.util.ArrayList;
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
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        if (root == null) {
        	return result;
        }
        
        // use LinkedList as a FIFO queue
        LinkedList<TreeNode> q = new LinkedList<TreeNode>(); // for node
        LinkedList<Integer> levelQ = new LinkedList<Integer>(); // for node level
        q.addLast(root);
        levelQ.addLast(1);
        
        int prevLevel = 0;
        while (q.size() > 0) {
        	TreeNode curNode = q.remove();
        	int curLevel = levelQ.remove();
        	if (curNode.left != null) {
        		q.addLast(curNode.left);
        		levelQ.add(curLevel+1);
        	}
        	if (curNode.right != null) {
        		q.addLast(curNode.right);
        		levelQ.add(curLevel+1);
        	}
        	
        	if (curLevel > prevLevel) {
        		ArrayList<Integer> arr = new ArrayList<Integer>();
        		arr.add(curNode.val);
        		result.add(arr);
        	} else {
        		result.get(result.size()-1).add(curNode.val);
        	}
        	prevLevel = curLevel;
        }
        
        return result;
    }
}
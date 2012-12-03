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
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        if (root == null) {
        	return result;
        }
        
        // use LinkedList as a FIFO queue
        LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
        LinkedList<Integer> level1 = new LinkedList<Integer>();
        LinkedList<TreeNode> q2 = new LinkedList<TreeNode>(); 
        LinkedList<Integer> level2 = new LinkedList<Integer>();
        
        q1.add(root);
        level1.add(1);
        boolean zigzag = true;
        int prevLevel = 0;
        
        while (q1.size() > 0) {
        	TreeNode curNode;
        	int curLevel;
        	if (zigzag) {
        		curNode = q1.remove();
        		curLevel = level1.remove();
        	} else {
        		curNode = q1.removeLast();
        		curLevel = level1.removeLast();
        	}
        	
        	if (curLevel > prevLevel) {
        		ArrayList<Integer> newArr = new ArrayList<Integer>();
        		newArr.add(curNode.val);
        		result.add(newArr);
        	} else {
        		if (zigzag) {
        			result.get(result.size()-1).add(curNode.val);
        		} else {
        			result.get(result.size()-1).add(0, curNode.val);
        		}
        	}
        	
        	if (zigzag) {
        		if (curNode.left != null) {
        			q2.addFirst(curNode.left);
        			level2.addFirst(curLevel+1);
        		} 
        		if (curNode.right != null) {
        			q2.addFirst(curNode.right);
        			level2.addFirst(curLevel+1);
        		}
        	} else {
        		if (curNode.left != null) {
        			q2.add(curNode.left);
        			level2.add(curLevel+1);
        		}
        		if (curNode.right != null) {
        			q2.add(curNode.right);
        			level2.add(curLevel+1);
        		}
        	}
        	
        	if (q1.size() == 0) {
        		q1 = new LinkedList<TreeNode>(q2);
        		q2.clear();
        		level1 = new LinkedList<Integer>(level2);
        		level2.clear();
        		zigzag = !zigzag;
        	}
        	
        	prevLevel = curLevel;
        }

        return result;
    }
}
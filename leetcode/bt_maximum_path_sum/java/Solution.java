import java.util.HashMap;
import java.util.Map;

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
    
	private Map<TreeNode, Integer> nodeMaxSubtreeMap = new HashMap<TreeNode, Integer>();
	
    public int maxPathSum(TreeNode root) {
    	return maxPathSumRecursive(root, 0);
    }
    	
    private int maxPathSumRecursive(TreeNode root, int level) {
    	if (level == 0) {
    		this.nodeMaxSubtreeMap.clear();
    	}
    	
        // Start typing your Java solution below
        // DO NOT write main() function
        if (root == null) {
        	return -65535;
        }
        
        // pre-order traversal to figure out 
        // max path sum from root
        // to two nodes, one is either sub tree
        int leftPostSum;
        if (!this.nodeMaxSubtreeMap.containsKey(root.left)) {
        	leftPostSum = postorder(root.left);
        } else {
        	leftPostSum = this.nodeMaxSubtreeMap.get(root.left);
        }
        int rightPostSum;
        if (!this.nodeMaxSubtreeMap.containsKey(root.right)) {
        	rightPostSum = postorder(root.right);
        } else {
        	rightPostSum = this.nodeMaxSubtreeMap.get(root.right);
        }
        int rootMaxSum = root.val 
        				+ (leftPostSum > 0 ? leftPostSum : 0)
        				+ (rightPostSum > 0 ? rightPostSum : 0);
        
        int maxSum;
        int leftMaxSum = maxPathSumRecursive(root.left, level+1);
        int rightMaxSum = maxPathSumRecursive(root.right, level+1);
        
        if (leftMaxSum > rightMaxSum) {
        	maxSum = leftMaxSum;
        } else {
        	maxSum = rightMaxSum;
        }
        
        return maxSum > rootMaxSum ? maxSum : rootMaxSum;
    }
    
    private int postorder(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int leftMaxSum = postorder(root.left);
    	if (leftMaxSum < 0) {
    		leftMaxSum = 0;
    	}
    	int rightMaxSum = postorder(root.right);
    	if (rightMaxSum < 0) {
    		rightMaxSum = 0;
    	}
    	int maxSubtreeSum;
    	if (leftMaxSum > rightMaxSum) {
    		maxSubtreeSum = root.val + leftMaxSum;
    	} else {
    		maxSubtreeSum = root.val + rightMaxSum;
    	}
    	
    	this.nodeMaxSubtreeMap.put(root, maxSubtreeSum);
    	return maxSubtreeSum;
    }
}
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	HashMap<Integer, HashMap<Integer, ArrayList<TreeNode>>> cache = null;
	
    public ArrayList<TreeNode> generateTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	cache = new HashMap<Integer, HashMap<Integer, ArrayList<TreeNode>>>();
    	return generateTrees(1, n);
    }
    
    private ArrayList<TreeNode> generateTrees(int s, int e) {
    	if (s > e) {
    		ArrayList<TreeNode> result = new ArrayList<TreeNode>();
    		result.add(null);
    		return result;
    	}
    	
    	// have solved this sub-problem before
    	if (cache.containsKey(s) && cache.get(s).containsKey(e)) {
    		return cache.get(s).get(e);
    	}
    	
    	ArrayList<TreeNode> result = new ArrayList<TreeNode>();
    	// first time confront this subproblem
    	for (int i = s; i <= e; ++i) {
    		// take i as the root
    		ArrayList<TreeNode> leftChilden = generateTrees(s, i-1);
    		ArrayList<TreeNode> rightChildren = generateTrees(i+1, e);
    		for (TreeNode left : leftChilden) {
    			for (TreeNode right : rightChildren) {
    				TreeNode root = new TreeNode(i);
    				root.left = left;
    				root.right = right;
    				result.add(root);
    			}
    		}
    	}
    	
    	return result;
    }
}

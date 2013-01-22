import java.util.ArrayList;


public class Solution {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> stack = new ArrayList<Integer>();
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	result.clear();
    	if (root == null) {
    		return result;
    	}
    	
    	pathSumRecursive(root, sum);
    	return result;
    }
    
    private void pathSumRecursive(TreeNode root, int sum) {
    	stack.add(root.val);
    	if (root.left == null && root.right == null && root.val == sum) {
    		@SuppressWarnings("unchecked")
			ArrayList<Integer> solution = (ArrayList<Integer>) stack.clone();
    		result.add(solution);
    	} else {
    		if (root.left != null) {
    			pathSumRecursive(root.left, sum - root.val);
    		}
    		if (root.right != null) {
    			pathSumRecursive(root.right, sum - root .val);
    		}
    	}
		stack.remove(stack.size()-1);
    }
}

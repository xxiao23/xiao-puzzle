import java.util.HashMap;
import java.util.Map;


public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
    	Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
    	for (int i = 0; i < inorder.length; ++i) {
    		inorderMap.put(inorder[i], i);
    	}
    	
    	Map<Integer, Integer> postorderMap = new HashMap<Integer, Integer>();
    	for (int p = 0; p < postorder.length; ++p) {
    		postorderMap.put(postorder[p], p);
    	}
    	
    	TreeNode root = buildTreeRecursive(inorderMap, inorder, 0, inorder.length-1,
    			postorderMap, postorder, 0, postorder.length-1);
    	
    	return root;
    }
    
    private TreeNode buildTreeRecursive(Map<Integer, Integer> inorderMap, int[] inorder,
    		int istart, int iend,
    		Map<Integer, Integer> postorderMap,
    		int[] postorder, int pstart, int pend) {

    	TreeNode root = null;
    	
    	if (pstart == pend) {
    		root = new TreeNode(postorder[pstart]);
    	}
    	
    	if (pstart > pend || pstart < 0 || pend < 0) {
    		return root;
    	}
    	
    	root = new TreeNode(postorder[pend]);
    	int rootInorderIndex = inorderMap.get(postorder[pend]);    	
    	int rightChildSize = iend - rootInorderIndex;
    	int leftChildSize = rootInorderIndex - istart;
    	if (leftChildSize > 0) {
    		TreeNode leftChild = buildTreeRecursive(inorderMap, inorder,
    			istart, rootInorderIndex-1,
    			postorderMap, postorder, pstart, pend-rightChildSize-1);
        	root.left = leftChild;

    	}
    	if (rightChildSize > 0) {
    		TreeNode rightChild = buildTreeRecursive(inorderMap, inorder,
    			rootInorderIndex+1, iend,
    			postorderMap, postorder, pend-rightChildSize, pend-1);
    		root.right = rightChild;
    	}    	
    	
    	return root;
    }
}
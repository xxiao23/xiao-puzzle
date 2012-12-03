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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
    	// preorder same as inorder
    	// tree node only have right child
    	if (preorder.length > 0 && trivialCase1(preorder, inorder)) {
    		TreeNode[] nodes = new TreeNode[preorder.length];
    		for (int i = 0; i < preorder.length; ++i) {
    			nodes[i] = new TreeNode(preorder[i]);
    			if (i > 0) {
    				nodes[i-1].right = nodes[i];
    			}
    		}
    		return nodes[0];
    	}
    	
    	// preorder same as reversed inorder
    	// tree node only have left child
    	if (preorder.length > 0 && trivialCase2(preorder, inorder)) {
    		TreeNode[] nodes = new TreeNode[preorder.length];
    		for (int i = 0; i < preorder.length; ++i) {
    			nodes[i] = new TreeNode(preorder[i]);
    			if (i > 0) {
    				nodes[i-1].left = nodes[i];
    			}
    		}
    		return nodes[0];
    	}
    	
    	Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
    	for (int i = 0; i < inorder.length; ++i) {
    		inorderMap.put(inorder[i], i);
    	}
    	
    	Map<Integer, Integer> preorderMap = new HashMap<Integer, Integer>();
    	for (int p = 0; p < preorder.length; ++p) {
    		preorderMap.put(preorder[p], p);
    	}
    	
    	TreeNode root = buildTreeRecursive(inorderMap, inorder, 0, inorder.length-1,
    			preorderMap, preorder, 0, preorder.length-1);
    	
    	return root;
    }
    
    private boolean trivialCase1(int[] preorder, int[] inorder) {
    	for (int i = 0; i < preorder.length; i++) {
    		if (preorder[i] != inorder[i]) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean trivialCase2(int[] preorder, int[] inorder) {
    	int numOfNodes = preorder.length;
    	for (int i = 0; i < preorder.length; i++) {
    		if (preorder[i] != inorder[numOfNodes-1-i]) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private TreeNode buildTreeRecursive(Map<Integer, Integer> inorderMap, int[] inorder,
    		int istart, int iend,
    		Map<Integer, Integer> preorderMap,
    		int[] preorder, int pstart, int pend) {
    	
//    	System.out.println("istart " + istart + " iend " + iend + " pstart " + pstart + " pend " + pend); 
    	TreeNode root = null;
    	
    	if (pstart == pend) {
    		root = new TreeNode(preorder[pstart]);
    		return root;
    	}
    	
    	if (pstart > pend || pstart < 0 || pend < 0) {
    		return root;
    	}
    	
    	root = new TreeNode(preorder[pstart]);
    	int rootInorderIndex = inorderMap.get(preorder[pstart]);    	
    	int rightChildSize = iend - rootInorderIndex;
    	int leftChildSize = rootInorderIndex - istart;
    	if (leftChildSize > 0) {
    		TreeNode leftChild = buildTreeRecursive(inorderMap, inorder,
    			istart, rootInorderIndex-1,
    			preorderMap, preorder, pstart+1, pstart+leftChildSize);
        	root.left = leftChild;

    	}
    	if (rightChildSize > 0) {
    		TreeNode rightChild = buildTreeRecursive(inorderMap, inorder,
    			rootInorderIndex+1, iend,
    			preorderMap, preorder, pend-rightChildSize+1, pend);
    		root.right = rightChild;
    	}    	
    	
    	return root;
    }
}
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
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        // map inorder value to index
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        
        return worker(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inorderMap);
        
    }
    
    public TreeNode worker(int[] preorder, int pl, int pr, int[] inorder, int il, int ir, Map<Integer, Integer> inorderMap) {
        if (pl > pr) return null;
        int rootVal = preorder[pl];
        TreeNode root = new TreeNode(rootVal); // create the root
        int inorderIndex = inorderMap.get(rootVal); // find the root in the inorder array
        int leftTreeSize = inorderIndex - il; // left subtree size
        root.left = worker(preorder, pl+1, pl+leftTreeSize, inorder, il, inorderIndex-1, inorderMap); // construct left subtree
        root.right = worker(preorder, pl+leftTreeSize+1, pr, inorder, inorderIndex+1, ir, inorderMap); // construct rigth subtree
        return root;
    }
}

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // construct inorder map
        // element => index
        Map<Integer, Integer> imap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; ++i) {
            imap.put(inorder[i], i);
        }
        return worker(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, imap);
    }
    
    private TreeNode worker(int[] inorder, int i, int j, int[] postorder, int l, int k, Map<Integer, Integer> imap) {
        if (i > j || l > k) return null;
        // create root
        TreeNode root = new TreeNode(postorder[k]);
        int rootIndex = imap.get(postorder[k]);
        int rightSize = j-rootIndex;
        // create right subtree
        root.right = worker(inorder, rootIndex+1, j, postorder, k-rightSize, k-1, imap);
        // create left subtree
        root.left = worker(inorder, i, rootIndex-1, postorder, l, k-rightSize-1, imap);
        return root;
    }
}

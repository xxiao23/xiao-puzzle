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
    private Map<TreeNode, Integer> rootToLeafMax = null;

    public int maxPathSum(TreeNode root) {
        rootToLeafMax = new HashMap<TreeNode, Integer>();
        return postOrder(root);
    }
    
    private int postOrder(TreeNode root) {
        if (root.left == null && root.right == null) {
            rootToLeafMax.put(root, root.val);
            return root.val; // leaf node
        }
        
        // solutions fully contained in sub-trees
        int m = Integer.MIN_VALUE;
        if (root.left != null) {
            m = postOrder(root.left);
        }
        if (root.right != null) {
            int r = postOrder(root.right);
            if (m < r) m = r;
        }
        
        // solution pass through root
        int m2l = root.left == null ? 0 : rootToLeafMax.get(root.left);
        int m2r = root.right == null ? 0 : rootToLeafMax.get(root.right);
        int m2 = m2l > m2r ? root.val+m2l : root.val+m2r;
        if (m2 < root.val) m2 = root.val;
        rootToLeafMax.put(root, m2);
        
        int m3 = root.val;
        if (m2l > 0) m3 += m2l;
        if (m2r > 0) m3 += m2r;
        return m > m3 ? m : m3;
    }
}

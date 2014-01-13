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
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (root == null) return ret;
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode ln = null;
        while (!s.isEmpty()) {
            TreeNode n = s.pop();
            if ((n.left == null && n.right == null) || (n.right != null && n.right == ln) || (n.left != null && n.left == ln)) {
                ln = n;
                ret.add(n.val);
            } else {
                s.push(n);
                if (n.right != null) s.push(n.right);
                if (n.left != null) s.push(n.left);
            }
        }
        
        return ret;
    }
}

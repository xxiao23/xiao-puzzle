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
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (root == null) return ret;
        
        Set<TreeNode> st = new HashSet<TreeNode>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode n = s.pop();
            if (st.contains(n)) {
                ret.add(n.val);
            } else {
                st.add(n);
                if (n.right != null) s.push(n.right);
                s.push(n);
                if (n.left != null) s.push(n.left);
            }
        }
        return ret;
    }
}

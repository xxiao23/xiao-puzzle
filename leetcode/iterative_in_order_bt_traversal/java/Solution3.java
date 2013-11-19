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
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        while (!s.isEmpty() || cur != null) {
            if (cur != null) {
                s.push(cur);
                cur = cur.left;
            } else {
                TreeNode n = s.pop();
                ret.add(n.val);
                cur = n.right;
            }
        }
        return ret;
    }
}

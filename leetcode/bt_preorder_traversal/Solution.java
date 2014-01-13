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
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if (root == null) return ret;
        
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.removeLast();
            ret.add(cur.val);
            if (cur.right != null) stack.addLast(cur.right);
            if (cur.left != null) stack.addLast(cur.left);
        }
        
        return ret;
    }
}

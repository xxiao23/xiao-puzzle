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
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        Set<TreeNode> visitedNodes = new HashSet<TreeNode>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.removeLast();
            if (visitedNodes.contains(cur)) {
                ret.add(cur.val);
            } else {
                visitedNodes.add(cur);
                stack.addLast(cur);
                if (cur.right != null) stack.addLast(cur.right);
                if (cur.left != null) stack.addLast(cur.left);
            }
        }
        return ret;
    }
}

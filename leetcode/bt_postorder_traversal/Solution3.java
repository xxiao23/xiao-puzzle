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
        Deque<TreeNode> traverseStack = new ArrayDeque<TreeNode>();
        Deque<TreeNode> resultStack = new ArrayDeque<TreeNode>();
        
        // take the top of the 1st stack
        // save it in the 2nd stack
        // and push its right and left children 
        // to the 1st stack
        traverseStack.addLast(root);
        while (!traverseStack.isEmpty()) {
            TreeNode cur = traverseStack.removeLast();
            resultStack.addLast(cur);
            if (cur.left != null) traverseStack.addLast(cur.left);
            if (cur.right != null) traverseStack.addLast(cur.right);
        }
        
        // iterate through the 2nd stack as FILO
        // that's the post-traversal order
        while (!resultStack.isEmpty()) {
            ret.add(resultStack.removeLast().val);
        }
        return ret;
    }
}

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
    public void recoverTree(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) return;
        
        // iterative inorder traversal
        Deque<TreeNode> st = new ArrayDeque<TreeNode>();
        // two pair of nodes
        // the 1st in the 1st pair
        // and the 2nd in the 2nd pair
        // are swapped
        TreeNode[] problemNodes = new TreeNode[4]; 
        
        // if current node has a left child,
        // move to the left
        // and save the current node in the stack
        // otherwise,
        // pop out the stack top
        // move to its right
        st.addLast(root);
        TreeNode cur = root.left;
        TreeNode inorderPrev = null, inorderCur = null;
        while (cur != null || !st.isEmpty()) {
            if (cur != null) {
                // move to the left
                st.addLast(cur);
                cur = cur.left;
            } else {
                // pop the stack top
                TreeNode n = st.removeLast();
                inorderPrev = inorderCur;
                inorderCur = n;
                if (inorderPrev != null && inorderPrev.val > inorderCur.val) {
                    if (problemNodes[0] == null) {
                        // found the 1st pair
                        problemNodes[0] = problemNodes[2] = inorderPrev;
                        problemNodes[1] = problemNodes[3] = inorderCur;
                    } else {
                        // found the 2nd pair
                        problemNodes[2] = inorderPrev;
                        problemNodes[3] = inorderCur;
                    }
                }
                // go to its right
                cur = n.right;
            }
        }
        
        // swap problemNodes[0] and problemNodes[3]
        int tmpVal = problemNodes[0].val;
        problemNodes[0].val = problemNodes[3].val;
        problemNodes[3].val = tmpVal;
    }
}

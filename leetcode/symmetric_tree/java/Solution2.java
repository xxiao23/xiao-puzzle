/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution2 {
    public boolean isSymmetric(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (root == null) return true;
        ArrayList<String> l = new ArrayList<String>();
        Queue<TreeNode> q1 = new LinkedList<TreeNode>(); // cur level
        Queue<TreeNode> q2 = new LinkedList<TreeNode>(); // next level
        int c1 = 1, c2 = 0;
        q1.add(root);
        while (c1 > 0) {
            TreeNode n = q1.remove();
            if (n == null) {
                l.add("#");
                continue;
            }
            c1--;
            l.add(Integer.valueOf(n.val).toString());
            q2.add(n.left);
            if (n.left != null) c2++;
            q2.add(n.right);
            if (n.right != null) c2++;
            if (c1 == 0) {
                while (!q1.isEmpty()) {
                    q1.remove();
                    l.add("#");
                }
                // check if l is symmetric
                int i = 0, j = l.size()-1;
                while (i < j) {
                    if (!l.get(i).equals(l.get(j))) return false;
                    i++;
                    j--;
                }
                l.clear();
                // swap q1 and q2
                Queue<TreeNode> tmp = q1;
                q1 = q2;
                q2 = tmp;
                // swap c1 and c2
                c1 = c2;
                c2 = 0;
            }
        }
        
        return true;
    }
}

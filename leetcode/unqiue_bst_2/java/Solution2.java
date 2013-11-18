/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution2 {
    static HashMap<Integer, HashMap<Integer, ArrayList<TreeNode>>> c = null; // 1st key is n, 2nd key is the start integer
    public ArrayList<TreeNode> generateTrees(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (c == null) {
            c = new HashMap<Integer, HashMap<Integer, ArrayList<TreeNode>>>();
            ArrayList<TreeNode> l = new ArrayList<TreeNode>();
            l.add(null);
            HashMap<Integer, ArrayList<TreeNode>> e = new HashMap<Integer, ArrayList<TreeNode>>();
            e.put(1, l);
            c.put(0, e);
        }
        
        return worker(n, 1);
    }
    
    private ArrayList<TreeNode> worker(int n, int start) {
        if (c.containsKey(n)) {
            if (n == 0) return c.get(0).get(1);
            
            if (c.get(n).containsKey(start)) {
                return c.get(n).get(start);
            } else {
                int t = start-1;
                ArrayList<TreeNode> l = new ArrayList<TreeNode>();
                for (TreeNode r : c.get(n).get(1)) {
                    l.add(cloneTree(r, t));
                }
                c.get(n).put(start, l);
                return l;
            }
        }
        
        ArrayList<TreeNode> l = new ArrayList<TreeNode>();
        for (int i = 0; i < n; ++i) {
            // i nodes on the left
            // n-1-i noes on the right
            ArrayList<TreeNode> ls = worker(i, 1);
            ArrayList<TreeNode> rs = worker(n-1-i, i+2);
            for (TreeNode lc : ls) {
                for (TreeNode rc :rs) {
                    TreeNode r = new TreeNode(i+1);
                    r.left = lc;
                    r.right = rc;
                    l.add(r);
                }
            }
        }
        if (!c.containsKey(n)) {
            HashMap<Integer, ArrayList<TreeNode>> e = new HashMap<Integer, ArrayList<TreeNode>>();
            c.put(n, e);
        }
        c.get(n).put(start, l);
        return l;
    }
    
    private TreeNode cloneTree(TreeNode root, int inc) {
        TreeNode nr = new TreeNode(root.val+inc);
        if (root.left != null) nr.left = cloneTree(root.left, inc);
        if (root.right != null) nr.right = cloneTree(root.right, inc);
        return nr;
    }
}

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (head == null) {
        	return null;
        }
        
        ListNode cur = head;
        ArrayList<Integer> num = new ArrayList<Integer>();
        while (cur != null) {
        	num.add(cur.val);
        	cur = cur.next;
        }
        
        return buildBSTRecursive(num, 0, num.size()-1);
    }
    
    private TreeNode buildBSTRecursive(ArrayList<Integer> num, int start, int end) {
    	if (start > end) {
    		return null;
    	}
    	
    	int mid = (start+end)/2;
    	TreeNode root = new TreeNode(num.get(mid));
    	root.left = buildBSTRecursive(num, start, mid-1);
    	root.right = buildBSTRecursive(num, mid+1, end);
    	return root;
    }
}
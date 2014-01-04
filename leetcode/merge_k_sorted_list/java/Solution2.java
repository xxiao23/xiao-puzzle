/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) return null;
        ListNode ph = new ListNode(0);
        ListNode last = ph;
        // use a min priority queue to 
        // get the next smallest node to add
        PriorityQueue<ListNode> mh = new PriorityQueue<ListNode>(lists.size(), new NodeComp());
        for (ListNode ln : lists) {
            if (ln != null) mh.add(ln);
        }
        while (mh.size() > 0) {
            ListNode nn = mh.poll();
            last.next = nn;
            last = nn;
            if (nn.next != null) mh.add(nn.next);
        }
        return ph.next;
    }
    
    private static class NodeComp implements Comparator<ListNode> {
        @Override
        public int compare(ListNode a, ListNode b) {
            if (a == null && b == null) return 0;
            if (a == null) return -1;
            if (b == null) return 1;
            return a.val-b.val;
        }
    }
}

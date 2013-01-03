import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode h = new ListNode(0);
        
        PriorityQueue<ListNode> q = new PriorityQueue<ListNode>(lists.size()+1,
        		new Comparator<ListNode>() {

					@Override
					public int compare(ListNode arg0, ListNode arg1) {
						if (arg0 == null) {
							return -1;
						}
						if (arg1 == null) {
							return 1;
						}
						
						return arg0.val - arg1.val;
					}
        	
        });
        
        for (int i = 0; i < lists.size(); ++i) {
        	ListNode t = lists.get(i);
        	if (t != null) {
        		q.add(t);
        	}
        }
        
        ListNode prevNode = h;
        while (q.size() > 0) {
        	ListNode qn = q.poll();
        	ListNode nn = new ListNode(qn.val);
        	prevNode.next = nn;
        	prevNode = nn;
        	if (qn.next != null) {
        		qn = qn.next;
        		q.add(qn);
        	}
        }
        
        return h.next;
        
    }
}

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

// failed the large case
public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (start.equals(end)) {
        	return 0;
        }
        
        // build a undirected graph
        HashSet<String> visited = new HashSet<String>();
        // bfs queue
        LinkedList<GraphNode> q = new LinkedList<GraphNode>();
        GraphNode s = new GraphNode();
        s.val = start;
        s.d = 1;
        q.add(s);
        
        GraphNode cur = null;
        boolean found = false;
        // BFS as needed
        while (q.isEmpty() == false) {
        	cur = q.removeFirst();
        	
        	// check if cur.val can be changed to end string
        	int diff = 0;
        	for (int i = 0; i < cur.val.length(); i++) {
        		if (cur.val.charAt(i) != end.charAt(i)) {
        			diff++;
        		}
        	}
        	if (diff == 1) {
        		found = true;
        		break;
        	}
        	
        	// explore more
        	Iterator<String> it = dict.iterator();
        	while (it.hasNext()) {
        		String t = it.next();
        		if (visited.contains(t)) {
        			continue;
        		}
        		diff = 0;
        		for (int i = 0; i < t.length(); ++i) {
        			if (cur.val.charAt(i) != t.charAt(i)) {
        				diff++;
        			}
        		}
        		if (diff == 1) {
        			GraphNode n = new GraphNode();
        			n.val = t;
        			n.d = cur.d+1;
        			visited.add(t);
        			q.add(n);
        		}
        	}
        }
        
        if (found) {
        	return cur.d+1;
        } else {
        	return 0;
        }
    }
    
    static class GraphNode {
    	public String val = null;
    	int d = Integer.MAX_VALUE;
    	
		@Override
		public String toString() {
			return "GraphNode [val=" + val + ", d=" + d + "]";
		}
    }
}

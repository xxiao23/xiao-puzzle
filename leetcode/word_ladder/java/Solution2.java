import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// passed the large test case
public class Solution2 {
    public int ladderLength(String start, String end, HashSet<String> dict) {
    	// preprocess the dict
    	// put words into groups
    	// where only 1 position is different among group memebers
    	List<HashMap<String, HashSet<String>>> g = 
    			new ArrayList<HashMap<String, HashSet<String>>>();
    	for (int i = 0; i < start.length(); ++i) {
    		g.add(new HashMap<String, HashSet<String>>());
    	}
    	Iterator<String> it = dict.iterator();
    	while (it.hasNext()) {
    		String t = it.next();
    		char[] tc = t.toCharArray();
    		char[] tt = new char[tc.length-1];
    		for (int i = 0; i < tc.length; ++i) {
    			int k = 0;
    			for (int j = 0; j < tc.length; ++j) {
    				if (j == i) {
    					continue;
    				}
    				tt[k++] = tc[j];
    			}
    			String tts = new String(tt);
    			if (!g.get(i).containsKey(tts)) {
    				HashSet<String> hs = new HashSet<String>();
    				g.get(i).put(tts, hs);
    			}
    			g.get(i).get(tts).add(t);
    		}
    	}
    	
    	// BFS
    	LinkedList<GraphNode> q = new LinkedList<GraphNode>();
    	HashSet<String> visited = new HashSet<String>();
    	GraphNode s = new GraphNode();
    	s.val = start;
    	s.d = 1;
    	q.add(s);
		visited.add(start);
    	GraphNode cur = null;
    	boolean found = false;
    	while (!q.isEmpty()) {
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
        	// explore futher
        	char[] cc = cur.val.toCharArray();
        	char[] ct = new char[start.length()-1];
        	for (int i = 0; i < start.length(); ++i) {
        		int k = 0;
        		for (int j = 0; j < start.length(); ++j) {
        			if (j == i) {
        				continue;
        			}
        			ct[k++] = cc[j];
        		}
        		String cs = new String(ct);
        		if (!g.get(i).containsKey(cs)) {
        			continue;
        		}
        		for(String ss : g.get(i).get(cs)) {
        			if (visited.contains(ss)) {
        				continue;
        			}
        			GraphNode nn = new GraphNode();
        			nn.val = ss;
        			nn.d = cur.d+1;
        			q.add(nn);
        			visited.add(ss);
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

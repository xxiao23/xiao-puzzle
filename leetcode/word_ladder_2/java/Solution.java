import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solution {
	private ArrayList<ArrayList<String>> result = null;
	private int minSteps = Integer.MAX_VALUE;
	HashMap<String, Integer> minDistToEnd = null;
	
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
    	result = new ArrayList<ArrayList<String>>();
    	minSteps = Integer.MAX_VALUE;
    	
    	// add end string to dict
    	dict.add(end);
    	
    	long startTime = System.currentTimeMillis();
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
    	long endTime   = System.currentTimeMillis();
    	long totalTime = endTime - startTime;
    	System.out.println("put words in 1-diff groups : " + totalTime);
    	startTime = endTime;
    	
    	// pre-compute legitimate next stops for each string
    	HashMap<String, HashSet<String>> nextHops = new HashMap<String, HashSet<String>>();
    	for (String t : dict) {
    		HashSet<String> ns = new HashSet<String>();
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
    			ns.addAll(g.get(i).get(tts));
    		}
    		ns.remove(t);
    		nextHops.put(t, ns);
    	}
    	// compute where to go from start string
    	HashSet<String> ns = new HashSet<String>();
		char[] tc = start.toCharArray();
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
			if (g.get(i).get(tts) != null) {
				ns.addAll(g.get(i).get(tts));
			}
		}
		nextHops.put(start, ns);
		endTime   = System.currentTimeMillis();
    	totalTime = endTime - startTime;
    	System.out.println("compute next hops : " + totalTime);
    	startTime = endTime;
    	
		// BFS from start string
		// cap the maximum steps allowed from start string
    	LinkedList<GraphNode> q = new LinkedList<GraphNode>();
    	HashSet<String> visited = new HashSet<String>();
    	GraphNode s = new GraphNode();
    	s.val = start;
    	s.d = 0;
    	q.add(s);
		visited.add(start);
    	GraphNode cur = null;
    	boolean found = false;
    	while (!q.isEmpty()) {
    		cur = q.removeFirst();
    		// check if cur.val can be changed to end string
        	if (cur.val.equals(end)) {
        		found = true;
        		break;
        	}
        	// explore futher
        	if (!nextHops.containsKey(cur.val)) {
        		continue;
        	}
        	
    		for(String ss : nextHops.get(cur.val)) {
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
    	
    	if (found) {
    		minSteps = cur.d;
    	} else {
    		return result;
    	}
    	endTime   = System.currentTimeMillis();
    	totalTime = endTime - startTime;
    	System.out.println("BFS1 : " + totalTime);
    	startTime = endTime;
    	
    	// BFS from end string
    	// compute the min distances from any string
    	// to reach end string
    	q.clear();
    	visited.clear();
    	s.val = end;
    	s.d = 0;
    	q.add(s);
    	visited.add(end);
    	minDistToEnd = new HashMap<String, Integer>();
    	// the distance from end itself is 0
    	minDistToEnd.put(end, 0);
    	while (!q.isEmpty()) {
    		cur = q.removeFirst();
    		if (!nextHops.containsKey(cur.val)) {
    			continue;
    		}
    		for (String ss : nextHops.get(cur.val)) {
    			if (visited.contains(ss)) {
    				continue;
    			}
    			GraphNode nn = new GraphNode();
    			nn.val = ss;
    			nn.d = cur.d+1;
    			minDistToEnd.put(ss, nn.d);
    			q.add(nn);
    			visited.add(ss);
    		}
    	}
    	
		// DFS
    	System.out.println("max steps : " + minSteps);
    	// path constituents
    	LinkedList<String> p = new LinkedList<String>();
    	p.addLast(start);
    	// hash set of what's already in the path
    	HashSet<String> pSet = new HashSet<String>();
    	pSet.add(start);
    	for (String nextToStart : nextHops.get(start)) {
    		treeDFS(nextToStart, p, pSet, nextHops, end, 1);
    	}
    	endTime   = System.currentTimeMillis();
    	totalTime = endTime - startTime;
    	System.out.println("DFS : " + totalTime);
    	startTime = endTime;
    	
    	System.out.println("result : " + result);
    	return this.result;
    }
    
    private void treeDFS(String source,
    		LinkedList<String> p, HashSet<String> pSet,
    		HashMap<String, HashSet<String>> nextHops, String end, int step) {
    	// if the min steps from source to end
    	// exceeds minSteps-step
    	// no need to explore further
    	if (minDistToEnd.containsKey(source) == false ||
    		minDistToEnd.get(source)+step > minSteps) {
    		return;
    	}
    	
    	if (step == minSteps) {
	    	if (source.equals(end)) {
				// this is a legitimate path
	    		// add to the result
	    		p.addLast(end);
	    		System.out.println("find one path : " + p);
	    		result.add(new ArrayList<String>(p));
	    		p.removeLast();
	    	}
	    	return;
    	}
    	
    	// recursion
    	// explore further
    	p.addLast(source);
    	pSet.add(source);
    	System.out.println(p);
    	if (nextHops.containsKey(source)) {
	    	for (String ns : nextHops.get(source)) {
	    		if (pSet.contains(ns)) {
	    			continue;
	    		}
	    		treeDFS(ns, p, pSet, nextHops, end, step+1);
	    	}
    	}
    	pSet.remove(source);
    	p.removeLast();
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

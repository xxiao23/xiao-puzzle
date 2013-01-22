import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
    public String minWindow(String S, String T) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	Map<Character, ArrayList<Integer>> positionIndex = 
    			new HashMap<Character, ArrayList<Integer>>();
    	Map<Character, Integer> cIndex = new HashMap<Character, Integer>();
    	Map<Character, Integer> cCount = new HashMap<Character, Integer>();
    	
    	for (int i = 0; i < T.length(); ++i) {
    		char c = T.charAt(i);
    		if (cCount.containsKey(c) == false) {
    			cCount.put(c, 1);
    			positionIndex.put(c, new ArrayList<Integer>());
    			cIndex.put(c, 0);
    		} else {
    			int v = cCount.get(c);
    			cCount.put(c, v+1);
    		}
    	}
    	
    	// build position index for each char in T
    	for(int i = 0; i < S.length(); ++i) {
    		char c = S.charAt(i);
    		if (positionIndex.containsKey(c)) {
    			positionIndex.get(c).add(i);
    		}
    	}
    	
    	// build min heap
    	PriorityQueue<HeapNode> pq = new PriorityQueue<HeapNode>(S.length(), 
    		new Comparator<HeapNode>() {

			@Override
			public int compare(HeapNode arg0, HeapNode arg1) {
				if (arg0 == null) {
					return -1;
				}
				if (arg1 == null) {
					return 1;
				}
				
				return arg0.pos - arg1.pos;
			}
    		
    	});
    	
    	int l = S.length()-1;
    	int r = 0;
    	for(int i = 0; i < S.length(); ++i) {
    		char c = S.charAt(i);
    		if (cCount.containsKey(c) && cCount.get(c) > 0) {
    			int ci = cIndex.get(c);
    			int pos = positionIndex.get(c).get(ci);
    			if (pos < l) {
    				l = pos;
    			}
    			if (pos > r) {
    				r = pos;
    			}
    			pq.add(new HeapNode(c, pos));
    			cIndex.put(c, ci+1);
    			cCount.put(c, cCount.get(c)-1);
    		}
    	}
    	int ml = l, mr = r, mw = mr - ml + 1;
    	if (pq.size() < T.length()) {
    		return "";
    	}
    	
    	int tLength = T.length();
    	while (pq.size() == tLength) {
    		HeapNode hn = pq.poll();
    		l = hn.pos;
    		int w = r - l + 1;
    		if (w < mw) {
    			ml = l;
    			mr = r;
    			mw = w;
    		}
    		ArrayList<Integer> positions = positionIndex.get(hn.c);
    		int curIndex = cIndex.get(hn.c);
    		if (curIndex < positions.size()) {
    			cIndex.put(hn.c, curIndex+1);
    			int newPos = positions.get(curIndex);
    			pq.add(new HeapNode(hn.c, newPos));
    			if (newPos > r) {
    				r = newPos;
    			}
    		}
    	}
    	
    	return S.substring(ml, mr+1);
    }
    
    private static class HeapNode {
    	public char c; // the character
    	public int pos; // actual position in string
    	
    	public HeapNode(char c, int pos) {
    		this.c = c;
    		this.pos = pos;
    	}

		@Override
		public String toString() {
			return "HeapNode [c=" + c + ", pos=" + pos + "]";
		}
    	
    }
}

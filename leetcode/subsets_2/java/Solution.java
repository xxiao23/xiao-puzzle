import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;


public class Solution {
	ArrayList<ArrayList<Integer>> result = null;
	TreeMap<Integer, Integer> m = null;
	
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());
        
        if (num.length == 0) {
        	return result;
        }
        
        // create a map
        // key -> distinct element in the array
        // value -> the number of times the element appears
        m = new TreeMap<Integer, Integer>();
        for (int i = 0; i < num.length; ++i) {
        	if (!m.containsKey(num[i])) {
        		m.put(num[i], 1);
        	} else {
        		m.put(num[i], m.get(num[i])+1);
        	}
        }
        ArrayList<Integer> keyList = new ArrayList<Integer>();
        Iterator<Integer> it = m.keySet().iterator();
        while (it.hasNext()) {
        	keyList.add(it.next());
        }
        
        ArrayList<Integer> cur = new ArrayList<Integer>();
        subsetsWithDupRecursive(keyList.toArray(new Integer[]{}), 0, cur);
        return result;
    }
    
    @SuppressWarnings("unchecked")
	private void subsetsWithDupRecursive(Integer[] keys, int startIndex, ArrayList<Integer> cur) {
    	for (int i = startIndex; i < keys.length; ++i) {
    		if (m.get(keys[i]) > 0) {
	    		cur.add(keys[i]);
	    		m.put(keys[i], m.get(keys[i])-1);
	    		result.add((ArrayList<Integer>) cur.clone());
	    		subsetsWithDupRecursive(keys, i, cur);
	    		cur.remove(cur.size()-1);
	    		m.put(keys[i], m.get(keys[i])+1);
    		}
    	}
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeMap;


public class Solution {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	result.clear();
    	if (num.length == 0) {
    		return result;
    	}
    	
    	Arrays.sort(num);
    	TreeMap<Integer, Integer> used = new TreeMap<Integer, Integer>();
    	for (int i : num) {
    		int k = 0;
    		if (used.containsKey(i)) {
    			k = used.get(i);
    		}
    		used.put(i, k+1);
    	}
    	int[] list = new int[num.length];
    	permuteUniqueRecursive(num, used, list, 0);
        return result;
    }
    
    private void permuteUniqueRecursive(int[] num, TreeMap<Integer, Integer> used, int[] list, int k) {
    	if (k == num.length) {
    		ArrayList<Integer> op = new ArrayList<Integer>();
    		for (int i : list) {
    			op.add(i);
    		}
    		result.add(op);
    		return;
    	}
    	
    	Iterator<Integer> it = used.keySet().iterator();
    	while (it.hasNext()) {
    		Integer d = it.next();
    		if (used.get(d) == 0) {
    			continue;
    		}
    		int t = used.get(d);
    		used.put(d, t-1);
    		list[k] = d;
    		permuteUniqueRecursive(num, used, list, k+1);
    		used.put(d, t);
    	}
    }
}

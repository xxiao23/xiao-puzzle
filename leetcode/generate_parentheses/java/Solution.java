import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	private static Map<Integer, ArrayList<String>> resultCache =
			new HashMap<Integer, ArrayList<String>>();
	
    public ArrayList<String> generateParenthesis(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<String> result = recursiveWorker(n);
    	return result;
    }
    
    private ArrayList<String> recursiveWorker(int n) {
    	if (n <= 0) {
    		return null;
    	}
    	
    	if (resultCache.containsKey(n)) {
    		return resultCache.get(n);
    	}
    	
    	ArrayList<String> result = new ArrayList<String>();
    	for (int i = 1; i < 2*n; i+=2) {
    		int l1 = 1;
    		int r1 = i-1;
    		int l2 = i+1;
    		int r2 = 2*n-1;
    		ArrayList<String> subResult1 = recursiveWorker((r1-l1+1)/2);
    		ArrayList<String> subResult2 = recursiveWorker((r2-l2+1)/2);

    		if (subResult1 != null && subResult2 != null) {
    			for(String s1 : subResult1) {
    				for (String s2 : subResult2) {
    					result.add("("+s1+")"+s2);
    				}
    			}
    		} else if (subResult1 != null) {
    			for (String s1 : subResult1) {
    				result.add("("+s1+")");
    			}
    		} else if (subResult2 != null) {
    			for (String s2 : subResult2) {
    				result.add("()"+s2);
    			}
    		} else {
    			result.add("()");
    		}
    	}
    	
    	resultCache.put(n, result);
    	return result;
    }
}

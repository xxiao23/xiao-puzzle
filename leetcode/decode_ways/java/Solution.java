import java.util.HashMap;
import java.util.Map;

public class Solution {
	
	static Map<Integer, Integer> subSolutionCache = new HashMap<Integer, Integer>();
	
    public int numDecodings(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if ("".equalsIgnoreCase(s)) {
    		return 0;
    	}
    	
    	subSolutionCache.clear();
    	return numDecodingsRecursive(s, s.length()-1);
     
    }
    
    private int numDecodingsRecursive(String s, int end) {
    	if (end < 0) {
    		return 1;
    	}
    	
    	if (subSolutionCache.containsKey(end)) {
    		return subSolutionCache.get(end);
    	}
    	int result = 0;
    	int lastDigit = Integer.valueOf(s.substring(end, end+1));
    	if (lastDigit > 0 && lastDigit <= 9) {
    		result += numDecodingsRecursive(s, end-1);
    	}
    	if (end > 0) { 
    		int lastTwoDigit = Integer.valueOf(s.substring(end-1, end+1));
    		if (lastTwoDigit <= 26 && lastTwoDigit > 9) {
    			result += numDecodingsRecursive(s, end-2);
    		}
    	}
    	subSolutionCache.put(end, result);
    	return result;
    }
}
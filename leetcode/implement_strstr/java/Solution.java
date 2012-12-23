import java.util.HashMap;
import java.util.Map;


public class Solution {
    public String strStr(String haystack, String needle) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int m = needle.length();
    	if (m == 0) {
    		return haystack;
    	}
    	
    	// KMP string matching
    	
    	int n = haystack.length();
    	// compute prefix function
    	Map<Integer, Integer> prefixMap = computePrefixFunction(needle);
    	int q = 0; // number of characters matched
    	for (int i = 1; i <= n; i++) {
    		while (q>0 && needle.charAt(q)!=haystack.charAt(i-1)) {
    			q = prefixMap.get(q);
    		}
    		if (needle.charAt(q) == haystack.charAt(i-1)) {
    			q += 1;
    		}
    		if (q == m) {
    			return haystack.substring(i-m);
    		}
    	}
    	return null;
        
    }
    
    private Map<Integer, Integer> computePrefixFunction(String needle) {
    	Map<Integer, Integer> prefixMap = new HashMap<Integer, Integer>();
    	// q start from 1
    	// end at needle.length
    	prefixMap.put(1, 0);
    	int k = 0;
    	for (int q = 2; q <= needle.length(); q++) {
    		// only tricky part here
    		// like KMP
    		// here to try next possible matching shift
    		while (k>0 && needle.charAt(q-1)!= needle.charAt(k)) {
    			k = prefixMap.get(k);
    		}
    		if (needle.charAt(q-1) == needle.charAt(k)) {
    			k += 1;
    		}
    		prefixMap.put(q, k);
    	}
    	return prefixMap;
    }
}

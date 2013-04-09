import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;


public class Solution {
	
	// TODO 
	// use memorization
	Map<String, HashSet<String>> m = new HashMap<String, HashSet<String>>();
	public boolean isScramble(String s1, String s2) {
		m.clear();
		return isScrambleR(s1, s2);
	}
	
    private  boolean isScrambleR(String s1, String s2) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (s1.length() != s2.length()) {
    		return false;
    	}
    	
    	if (m.containsKey(s1) && m.get(s1).contains(s2)) {
    		return true;
    	}
    	
    	if (s1.length() == 0 && s2.length() == 0) {
    		return true;
    	}
    	
    	if (s1.length() == 1 && s2.length() == 1) {
    		return s1.equals(s2);
    	}
    	
    	int n = s1.length();
    	TreeMap<Character, Integer> cs1 = new TreeMap<Character, Integer>();
    	TreeMap<Character, Integer> cs2 = new TreeMap<Character, Integer>();

    	// two leaves under root is not swapped
    	for (int i = 0; i < n-1; ++i) {
    		// make sure the partitions
    		// have the same number of distinct characters
    		cs1.put(s1.charAt(i), cs1.containsKey(s1.charAt(i)) ? (cs1.get(s1.charAt(i)) + 1) : 1);
    		cs2.put(s2.charAt(i), cs2.containsKey(s2.charAt(i)) ? (cs2.get(s2.charAt(i)) + 1) : 1);
    		if (!cs1.equals(cs2)) {
    			continue;
    		}
    		
    		// recursively determine
    		// whether sub strings are scramble
    		if (isScrambleR(s1.substring(0, i+1), s2.substring(0, i+1)) 
    			&& isScrambleR(s1.substring(i+1), s2.substring(i+1))) {
    			if (!m.containsKey(s1)) {
    				m.put(s1, new HashSet<String>());
    			}
    			m.get(s1).add(s2);
    			return true;
    		}
    	}
    	
    	cs1.clear();
    	cs2.clear();
    	// two leaves under root is swapped
    	for (int i = 0; i < n-1; ++i) {
    		// make sure the partitions
    		// have the same number of distinct characters
    		cs1.put(s1.charAt(i), cs1.containsKey(s1.charAt(i)) ? (cs1.get(s1.charAt(i)) + 1) : 1);
    		cs2.put(s2.charAt(n-1-i), cs2.containsKey(s2.charAt(n-1-i)) ? (cs2.get(s2.charAt(n-1-i)) + 1) : 1);
    		if (!cs1.equals(cs2)) {
    			continue;
    		}
    		
    		// recursively determine
    		// whether sub strings are scramble
    		if (isScrambleR(s1.substring(0, i+1), s2.substring(n-i-1))
    			&& isScrambleR(s1.substring(i+1), s2.substring(0, n-i-1))) {
    			if (!m.containsKey(s1)) {
    				m.put(s1, new HashSet<String>());
    			}
    			m.get(s1).add(s2);
    			return true;
    		}
    	}
    	
    	return false;
    }
}

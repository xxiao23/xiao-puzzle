import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public ArrayList<String> anagrams(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<String> result = new ArrayList<String>();
    	Map<String, ArrayList<String>> anagramMap = new HashMap<String, ArrayList<String>>();
    	for (String s : strs) {
    		char[] chars = s.toCharArray();
    		Arrays.sort(chars);
    		String sortedStr = new String(chars);
    		if (anagramMap.containsKey(sortedStr)) {
    			anagramMap.get(sortedStr).add(s);
    		} else {
    			ArrayList<String> oneAnagramList = new ArrayList<String>();
    			oneAnagramList.add(s);
    			anagramMap.put(sortedStr, oneAnagramList);
    		}
    	}
    	
    	for (String keyStr : anagramMap.keySet()) {
    		if (anagramMap.get(keyStr).size() > 1) {
    			result.addAll(anagramMap.get(keyStr));
    		}
    	}
        return result;
    }
}

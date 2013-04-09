import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (L.length == 0) {
        	return result;
        }
        
        Map<String, Integer> words = new HashMap<String, Integer>();
        for(int i = 0; i < L.length; ++i) {
        	if (words.containsKey(L[i])) {
        		words.put(L[i], words.get(L[i])+1);
        	} else {
        		words.put(L[i], 1);
        	}
        }
        
        int wordLen = L[0].length();
        int wholeLen = wordLen * L.length;
        
        for (int i = 0; i+wholeLen-1 < S.length(); i++) {
        	int j = i;
        	boolean found = true;
        	HashMap<String, Integer> wordsClone = new HashMap<String, Integer>();
    		wordsClone.putAll(words);
        	while (j < i+wholeLen) {
        		String word = S.substring(j, j+wordLen);
        		if (!wordsClone.containsKey(word) || wordsClone.get(word) == 0) {
        			found = false;
        			break;
        		} else {
        			wordsClone.put(word, wordsClone.get(word)-1);
        		}
        		j += wordLen;
        	}
        	if (found) {
        		result.add(i);
        	}
        }
        return result;
    }
}

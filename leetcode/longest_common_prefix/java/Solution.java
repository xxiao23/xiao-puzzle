
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (strs.length == 0) {
    		return "";
    	}
    	
        int i = 0;
        int l = strs[0].length();
        boolean stop = false;
        while (i < l) {
        	char c = strs[0].charAt(i);
        	for (int j = 1; j < strs.length; ++j) {
        		if (strs[j].length() < i+1 || strs[j].charAt(i) != c) {
        			stop = true;
        			break;
        		}
        	}
        	if (stop) {
        		break;
        	}
        	++i;
        }
        
        return strs[0].substring(0, i);
    }
}

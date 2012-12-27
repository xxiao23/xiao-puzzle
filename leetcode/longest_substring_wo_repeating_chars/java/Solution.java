
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int maxL = 0;
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < s.length(); ++i) {
    		int j = sb.toString().indexOf(s.charAt(i));
    		if (j != -1) {
    			if (sb.length() > maxL) {
    				maxL = sb.length();
    			}
    			sb = new StringBuilder(sb.substring(j+1));
    		}
    		sb.append(s.charAt(i));
    	}
    	return Math.max(maxL, sb.length());
    }
}

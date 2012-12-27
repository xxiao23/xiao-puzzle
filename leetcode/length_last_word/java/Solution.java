
public class Solution {
    public int lengthOfLastWord(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int result = 0;
        boolean reset = true;
        for (int i = 0; i < s.length(); ++i) {
        	if (s.charAt(i) == ' ') {
        		reset = true;
        	} else {
        		if (reset) {
        			result = 0;
        			reset = false;
        		}
        		result += 1;
        	}
        }
        return result;
    }
}

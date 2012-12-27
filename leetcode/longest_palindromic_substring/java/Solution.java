
public class Solution {
    public String longestPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int l = 0;
    	int i = 0;
    	boolean found = false;
        for (l = s.length()-1; l >=1; --l) {
        	for (i = 0; i+l < s.length(); ++i) {
        		if (isPalindrome(s, i, i+l)) {
        			found = true;
        			break;
        		}
        	}
        	if (found) {
        		break;
        	}
        }
        return s.substring(i, i+l+1);
    }
    
    private boolean isPalindrome(String s, int l, int r) {
    	while (l <= r) {
    		if (s.charAt(l++) != s.charAt(r--)) {
    			return false;
    		}
    	}
    	return true;
    }
}

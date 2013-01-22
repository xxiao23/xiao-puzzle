
public class Solution {
    public boolean isPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (s.length() == 0) {
        	return true;
        }
        
        int l = 0, r = s.length()-1;
        while (l <= r) {
        	while (l < s.length() && !isAlphaNumerical(s.charAt(l))) {
        		++l;
        	}
        	if (l >= s.length()) {
        		return true;
        	}
        	while (r >= 0 && !isAlphaNumerical(s.charAt(r))) {
         		--r;
         	}
        	if (r < 0) {
        		return true;
        	}
        	if (s.charAt(l) != s.charAt(r) && Math.abs(s.charAt(l)-s.charAt(r)) != (int)('a'-'A')) {
        		return false;
        	}
        	++l;
        	--r;
        }
        
        return true;
    }
    
    boolean isAlphaNumerical(char c) {
    	if (c >= 'a' && c <= 'z') {
    		return true;
    	}
    	if (c >= 'A' && c <= 'Z') {
    		return true;
    	}
    	if (c >= '0' && c <= '9') {
    		return true;
    	}
    	return false;
    }
}

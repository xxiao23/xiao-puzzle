
public class Solution {
    public boolean isPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	
    	int i = 0, j = s.length()-1;
    	while (i <= j) {
    		while (i < s.length() && !((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') ||
    		      (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') ||
    		      (s.charAt(i) >= '0' && s.charAt(i) <= '9'))) {
    			i++;
    		}
    		while (j >= 0 && !((s.charAt(j) >= 'a' && s.charAt(j) <= 'z') ||
      		      (s.charAt(j) >= 'A' && s.charAt(j) <= 'Z') ||
      		      (s.charAt(j) >= '0' && s.charAt(j) <= '9'))) {
      			j--;
      		}
    		
    		if (i >= s.length() || j < 0) {
    			break;
    		}
    		
    		String c1 = String.valueOf(s.charAt(i)).toLowerCase();
    		String c2 = String.valueOf(s.charAt(j)).toLowerCase();
    		if (c1.equals(c2) == false) {
    			return false;
    		}
    		i++;
    		j--;
    	}
    	
    	return true;
    }
}

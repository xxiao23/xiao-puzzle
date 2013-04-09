
public class Solution {
    public int atoi(String str) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	if ("".equals(str)) {
    		return 0;
    	}
    	// search for the first non whitespace char
    	int i = 0;
    	while (i < str.length() && str.charAt(i) == ' ') {
    		i++;
    	}
    	if (i == str.length()) {
    		return 0;
    	}
    	
    	// check if the fist non-whitespace char is a sign
    	int result = 0;
    	int sign = 1;
    	char d = str.charAt(i);
    	if (d == '-') {
    		sign = -1;
    		i++;
    	} else if (d == '+') {
    		sign = 1;
    		i++;
    	}
    	
    	int m = Integer.MAX_VALUE / 10;
    	while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
    		if (sign == 1 && (result > m || (result == m && str.charAt(i) > '7'))) {
    			return Integer.MAX_VALUE;
    		} else if (sign == -1 && (result > m || (result == m && str.charAt(i) > '8'))) {
    			return Integer.MIN_VALUE;
    		}
    		
    		result *= 10;
    		result += str.charAt(i) - '0';
    		i++;
    	}
    	
    	return sign*result;
    }
}

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Solution {
    public boolean isNumber(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	String ns = s.trim();
    	    	
    	String[] rex = { "[+-]?\\d+", "[+-]?\\d+\\.\\d*", "[+-]?\\d*\\.\\d+",
    			         "[+-]?\\d+(\\.\\d*)?e[+-]?\\d+", "[+-]?\\d*\\.\\d+e[+-]?\\d+"};
    	Pattern pattern;
    	for (String p : rex) {
    		pattern = Pattern.compile(p);
    		Matcher matcher = pattern.matcher(ns);
        	if (matcher.matches()) {
        		System.out.println(p + " : " + ns);
        		return true;
        	}
    	}
    	
    	return false;
    }
}

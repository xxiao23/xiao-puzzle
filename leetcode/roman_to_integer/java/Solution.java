import java.util.HashMap;
import java.util.Map;


public class Solution {
	static Map<Character, Integer> romanNums = new HashMap<Character, Integer>();
	static {
		romanNums.put('I', 1);
		romanNums.put('V', 5);
		romanNums.put('X', 10);
		romanNums.put('L', 50);
		romanNums.put('C', 100);
		romanNums.put('D', 500);
		romanNums.put('M', 1000);
	}
    public int romanToInt(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	int result = romanNums.get(s.charAt(s.length()-1));
    	int prev = 0;
    	int cur = result;
    	boolean add = true; // substract if false
        for (int i = s.length()-2; i >= 0; --i) {
        	prev = cur;
        	cur = romanNums.get(s.charAt(i));
        	
        	if (prev < cur) {
        		add = true;
        	} else if (prev > cur){
        		add = false;
        	}
        	
        	if (add) {
        		result += cur;
        	} else {
        		result -= cur;
        	}
        }
        
        return result;
    }
}

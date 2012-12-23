import java.util.HashMap;
import java.util.Map;


public class Solution {
	static Map<Integer, String> romanMap = new HashMap<Integer, String>();
    static {
    	for(int i = 0; i < 7; ++i) {
    		romanMap.put(1, "I");
    		romanMap.put(2, "II");
    		romanMap.put(3, "III");
    		romanMap.put(4, "IV");
    		romanMap.put(5, "V");
    		romanMap.put(6, "VI");
    		romanMap.put(7, "VII");
    		romanMap.put(8, "VIII");
    		romanMap.put(9, "IX");
    		romanMap.put(10, "X");
    		romanMap.put(20, "XX");
    		romanMap.put(30, "XXX");
    		romanMap.put(40, "XL");
    		romanMap.put(50, "L");
    		romanMap.put(60, "LX");
    		romanMap.put(70, "LXX");
    		romanMap.put(80, "LXXX");
    		romanMap.put(90, "XC");
    		romanMap.put(100, "C");
    		romanMap.put(200, "CC");
    		romanMap.put(300, "CCC");
    		romanMap.put(400, "CD");
    		romanMap.put(500, "D");
    		romanMap.put(600, "DC");
    		romanMap.put(700, "DCC");
    		romanMap.put(800, "DCCC");
    		romanMap.put(900, "CM");
    		romanMap.put(1000, "M");
    		romanMap.put(2000, "MM");
    		romanMap.put(3000, "MMM");
    	}
    }
    
    public String intToRoman(int num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        
        StringBuilder result = new StringBuilder();
        int d = 10000;
        int q = 0;
        for (int i = 3; i >= 0; i--) {
        	d /= 10;
        	q = num / d;
        	if (q > 0) {
        		result.append(romanMap.get(q*d));
        	}
        	num %= d; 
        }
        
        return result.toString();
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Solution {
	static Map<String, ArrayList<String>> numToLetterMap = new HashMap<String, ArrayList<String>>();
	
	static {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add(" ");
		numToLetterMap.put("0", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add(" ");
		numToLetterMap.put("1", tmp);
		
		tmp = new ArrayList<String>();		
		tmp.add("a");
		tmp.add("b");
		tmp.add("c");
		numToLetterMap.put("2", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("d");
		tmp.add("e");
		tmp.add("f");
		numToLetterMap.put("3", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("g");
		tmp.add("h");
		tmp.add("i");
		numToLetterMap.put("4", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("j");
		tmp.add("k");
		tmp.add("l");
		numToLetterMap.put("5", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("m");
		tmp.add("n");
		tmp.add("o");
		numToLetterMap.put("6", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("p");
		tmp.add("q");
		tmp.add("r");
		tmp.add("s");
		numToLetterMap.put("7", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("t");
		tmp.add("u");
		tmp.add("v");
		numToLetterMap.put("8", tmp);
		
		tmp = new ArrayList<String>();
		tmp.add("w");
		tmp.add("x");
		tmp.add("y");
		tmp.add("z");
		numToLetterMap.put("9", tmp);
	}
	
    public ArrayList<String> letterCombinations(String digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (digits.length() == 0) {
    		ArrayList<String> result = new ArrayList<String>();
    		result.add("");
    		return result;
    	}
    	
        return lcRecursive(digits);
    }
    
    private ArrayList<String> lcRecursive(String digits) {
    	if (digits.length() == 1) {
    		return numToLetterMap.get(digits);
    	}
    	
    	ArrayList<String> subResult = lcRecursive(digits.substring(0, digits.length()-1));
    	String lastDigit = digits.substring(digits.length()-1, digits.length());
    	ArrayList<String> lastLetters = numToLetterMap.get(lastDigit);
    	ArrayList<String> result = new ArrayList<String>();
    	for (String s1 : subResult) {
    		for (String s2 : lastLetters) {
    			String tmp = "";
    			if (!" ".equalsIgnoreCase(s1)) {
    				tmp += s1;
    			}
    			if (!" ".equalsIgnoreCase(s2)) {
    				tmp += s2;
    			}
    			result.add(tmp);
    		}
    	}
    	return result;
    }
}

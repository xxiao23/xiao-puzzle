import java.util.ArrayList;


public class Solution {
    @SuppressWarnings("unchecked")
	public ArrayList<String> restoreIpAddresses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (s == null || s.length() == 0) {
    		return new ArrayList<String>();
    	}
    	
        ArrayList<String>[] prevRow = new ArrayList[s.length()];
        ArrayList<String>[] curRow = new ArrayList[s.length()];
        for (int i = 0; i < s.length(); ++i) {
    		curRow[i] = new ArrayList<String>();
        	if (s.charAt(0) == '0' && i > 0) {
        		// leading 0
        		continue;
        	}
        	if (i >= 3) {
        		// more than 3 digits
        		continue;
        	}
        	String ss = s.substring(0, i+1);
        	int v = Integer.parseInt(ss);
        	if (v >= 0 && v <= 255) {
        		curRow[i].add(ss);
        	}
        }
        
        for (int k = 2; k <= 4; ++k) {
        	prevRow = curRow;
        	curRow = new ArrayList[s.length()];
        	for (int i = 0; i < s.length(); ++i) {
        		curRow[i] = new ArrayList<String>();
        		for (int j = 0; j < 3 && i-j > 0; ++j) {
        			if (s.charAt(i-j) == '0' && j > 0) {
        				// leading 0
        				continue;
        			}
        			String ss = s.substring(i-j, i+1);
        			int v = Integer.parseInt(ss);
        			if (v >= 0 && v <= 255) {
        				for (String ps : prevRow[i-j-1]) {
        					curRow[i].add(ps + "." + ss);
        				}
        			}
        		}
        	}
        }
        return curRow[s.length()-1];
        
    }
}

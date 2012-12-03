
public class Solution {
    public String addBinary(String a, String b) {
        // Start typing your Java solution below
        // DO NOT write main() function
        StringBuilder sum = new StringBuilder();
        boolean carryOver = false;
        int i = a.length() - 1;
        int j = b.length() - 1;
        while (i >=0 && j >= 0) {
        	char aChar = a.charAt(i);
        	char bChar = b.charAt(j);
        	if (aChar == '0') {
        		if (bChar == '0') {
        			// 0 + 0
        			if (carryOver) {
        				sum.insert(0, '1');
        			} else {
        				sum.insert(0, '0');
        			}
        			carryOver = false;
        		} else {
        			// 0 + 1
        			if (carryOver) {
        				sum.insert(0, '0');
        			} else {
        				sum.insert(0, '1');
        				carryOver = false;
        			}
        		}
        	} else {
        		if (bChar == '0') {
        			// 1 + 0
        			if (carryOver) {
        				sum.insert(0,  '0');
        			} else {
        				sum.insert(0, '1');
        			}
        		} else {
        			// 1 + 1
        			if (carryOver) {
        				sum.insert(0,  '1');
        			} else {
        				sum.insert(0, '0');
        			}
        			carryOver = true;
        		}
        	}
        	--i;
        	--j;
        }
        while (i >= 0) {
    		if (carryOver) {
    			char aChar = a.charAt(i);
    			if (aChar == '0') {
    				sum.insert(0, '1');
    				carryOver = false;
    			} else {
    				sum.insert(0, '0');
    			}
    		} else {
    			sum.insert(0,  a.charAt(i));
    		}
    		--i;
    	}
    	while (j >= 0) {
    		if (carryOver) {
    			char bChar = b.charAt(j);
    			if (bChar == '0') {
    				sum.insert(0, '1');
    				carryOver = false;
    			} else {
    				sum.insert(0, '0');
    			}
    		} else {
    			sum.insert(0,  b.charAt(j));
    		}
    		--j;
    	}
    	
    	if (carryOver) {
    		sum.insert(0, '1');
    	}
        return sum.toString();
    }
}

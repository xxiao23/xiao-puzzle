public class Solution {
	private static String elementOne = "1";
	
    public String countAndSay(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (n == 1) {
    		return elementOne;
    	}
    	
    	int i = 2;
    	String result = null;
    	String prevElement = "a" + elementOne;
    	while (i <= n) {
    		char prevChar;
    		char curChar = 'a';
    		StringBuilder curElement = new StringBuilder();
    		int count = 0;
    		for (int k = 1; k < prevElement.length(); ++k) {
    			curChar = prevElement.charAt(k);
    			prevChar = prevElement.charAt(k-1);
    			if (curChar == prevChar) {
    				++count;
    			} else {
    				if (count > 0) {
    					curElement.append(count);
    					curElement.append(prevChar);
    				}
    				count = 1;
    			}
    		}
    		curElement.append(count);
    		curElement.append(curChar);
    		result = curElement.toString();
    		System.out.println("i = " + i + " : " + curElement);  
    		prevElement = "a" + curElement.toString();
    		++i;
    	}
    	
    	return result;
    }
}
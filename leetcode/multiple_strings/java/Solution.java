
public class Solution {
    public String multiply(String num1, String num2) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (num1 == null || num2 == null) {
    		return null;
    	}
    	
    	String[] components = new String[num2.length()];
    	
    	// compute each component
    	for (int i = num2.length()-1; i >= 0; --i) {
    		// compute num1 * num2[i]
    		int b = Integer.valueOf(num2.substring(i, i+1));
    		StringBuilder sb = new StringBuilder();
    		int carryOver = 0;
    		for (int j = num1.length()-1; j >= 0; --j) {
    			int p = Integer.valueOf(num1.substring(j, j+1)) * b + carryOver;
    			sb.append(Integer.valueOf(p%10).toString());
    			carryOver = p / 10;
    		}
    		if (carryOver > 0) {
    			sb.append(Integer.valueOf(carryOver).toString());
    		}
    		components[i] = sb.reverse().toString();
    	}
    	
    	// add all components together
    	String result = components[num2.length()-1];
    	StringBuilder zeros = new StringBuilder();
    	for (int i = components.length-2; i >= 0; --i) {
    		zeros.append("0");
    		String b = components[i] + zeros.toString(); // times 10
    		int j = result.length() - 1;
    		int k = b.length() - 1;
    		int carryOver = 0;
    		StringBuilder sb = new StringBuilder();
    		while (j >= 0 && k >= 0) {
    			int s = Integer.valueOf(result.substring(j, j+1)) 
    					+ Integer.valueOf(b.substring(k, k+1)) + carryOver;
    			sb.append(Integer.valueOf(s%10).toString());
    			carryOver = s / 10;
    			--j;
    			--k;
    		}
    		while (j >= 0) {
    			int s = Integer.valueOf(result.substring(j, j+1)) + carryOver;
    			sb.append(Integer.valueOf(s%10).toString());
    			carryOver = s / 10;
    			--j;	
    		}
    		while (k >= 0) {
    			int s = Integer.valueOf(b.substring(k, k+1)) + carryOver;
    			sb.append(Integer.valueOf(s%10).toString());
    			carryOver = s / 10;
    			--k;
    		}
    		if (carryOver > 0) {
    			sb.append(Integer.valueOf(carryOver).toString());
    		}
    		result = sb.reverse().toString();
    	}
    	
    	int i = 0;
    	for (; i < result.length()-1; ++i) {
    		if (result.charAt(i) != '0') {
    			break;
    		}
    	}
    	return result.substring(i);
    }
}

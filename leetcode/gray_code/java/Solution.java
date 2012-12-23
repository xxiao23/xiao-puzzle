import java.util.ArrayList;
import java.util.Vector;

public class Solution {
    public ArrayList<Integer> grayCode(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	if (n == 0) {
    		result.add(0);
    		return result;
    	}
    	
    	int i = 2;
    	Vector<String> strResult = new Vector<String>();
    	strResult.add("0");
    	strResult.add("1");
    	while (i <= n) {
    		int len = strResult.size();
    		for (int j = len-1; j >= 0; --j) {
    			String s = strResult.get(j);
    			String cs = "1"+s; // complement gray code
    			s = "0"+s;
    			strResult.setElementAt(s, j);
    			strResult.add(cs);
    		}
    		i++;
    	}
    	
    	for (String s : strResult) {
    		result.add(Integer.valueOf(s, 2));
    	}
    	return result;
    }
    
}
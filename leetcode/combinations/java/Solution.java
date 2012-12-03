import java.util.ArrayList;

public class Solution {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    	
    	if (n < k) {
    		return result;
    	}
    	
        if (k == 1) {
        	for (int i = 1; i <= n; i++) {
        		ArrayList<Integer> oneComb = new ArrayList<Integer>();
        		oneComb.add(i);
        		result.add(oneComb);
        	}
        	return result;
        }
        
        ArrayList<ArrayList<Integer>> subResult1 = combine(n-1, k);
        result.addAll(subResult1);
        
        ArrayList<ArrayList<Integer>> subResult2 = combine(n-1, k-1);
        for (ArrayList<Integer> oneComb : subResult2) {
        	oneComb.add(n);
        }
        result.addAll(subResult2);
        
        return result;
    }
}
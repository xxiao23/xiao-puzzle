import java.util.ArrayList;

public class Solution {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	result.clear();
    	if (num.length == 0) {
    		return result;
    	}
    	
    	boolean[] used = new boolean[num.length];
    	int[] list = new int[num.length];
    	permuteRecursive(num, used, list, 0);
    	
    	return result;
    }
    
    private void permuteRecursive(int[] num, boolean[] used, int[] list, int k) {
    	if (k == num.length) {
    		ArrayList<Integer> op = new ArrayList<Integer>();
    		for (Integer i : list) {
    			op.add(i);
    		}
    		result.add(op);
    		return;
    	}
    	
    	for (int i = 0; i < num.length; ++i) {
    		if (used[i] == false) {
    			used[i] = true;
    			list[k] = num[i];
    			permuteRecursive(num, used, list, k+1);
    			used[i] = false;
    		}
    	}
    }
    
}

import java.util.ArrayList;


public class Solution {
	   public String getPermutation(int n, int k) {
	        // Start typing your Java solution below
	        // DO NOT write main() function
		   	ArrayList<Integer> nums = new ArrayList<Integer>();
		   	for (int i = 1; i <= n; i++) {
		   		nums.add(i);
		   	}
		   	int[] p = new int[n];
		   	p[0] = 1;
		   	for (int i = 1; i < n; ++i) {
		   		p[i] = i * p[i-1];
		   	}
		   	
		   	StringBuilder sb = new StringBuilder();
		   	int j = k-1;
		   	for (int i = n-1; i >= 0; --i) {
		   		int a = (j) / p[i];
		   		sb.append(nums.get(a));
		   		nums.remove(a);
		   		j %= p[i];
		   	}
		   	
		   	return sb.toString();
	    }
}

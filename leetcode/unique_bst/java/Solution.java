
public class Solution {
    public int numTrees(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int[] t = new int[n+1];
    	t[0] = 1;
    	
    	for (int i = 1; i < n+1; ++i) {
    		int s = 0;
    		for (int j = 0; j < i; ++j) {
    			s += t[j]*t[i-1-j];
    		}
    		t[i] = s;
    	}
    	return t[n];
    }
}

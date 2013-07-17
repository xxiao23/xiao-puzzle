import java.util.BitSet;


public class Solution {
    public int minCut(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int l = s.length();
    	if (l == 0) {
    		return 0;
    	}
    	
    	// pl[i][j] is true 
    	// if s[i..j] is a palindrome
    	BitSet[] pl = new BitSet[l];
    	for (int i = 0; i < l; ++i) {
    		pl[i] = new BitSet(l);
    	}
    	
    	// find all substrings those are palindromes
    	for (int i = 0; i < l; ++i) {
    		// find all palindromes centered at s[i]
    		int start = i;
    		int end = i;
    		while (start >= 0 && end < l && s.charAt(start) == s.charAt(end)) {
    			pl[start].set(end);
    			start--;
    			end++;
    		}
    		// find all palindromes centered at s[i]&s[i+1]
    		start = i;
    		end = i+1;
    		while (start >= 0 && end < l && s.charAt(start) == s.charAt(end)) {
    			pl[start].set(end);
    			start--;
    			end++;
    		}
    	}
    	
    	// dp[i] = min_cut in s[0..i]
    	int[] dp = new int[l];
    	dp[0] = 0;
    	for (int i = 1; i < l; i++) {
    		if (pl[0].get(i)) {
    			// s[0..i] is a palindrome
    			dp[i] = 0;
    			continue;
    		}
    		int mc = Integer.MAX_VALUE;
    		for (int k = i; k > 0; --k) {
    			if (pl[k].get(i)) {
    				// s[k..i] is a palindrome
    				// suppose it's the last one in the partition
    				mc = Math.min(mc, dp[k-1]+1);
    			}
    		}
    		dp[i] = mc;
    	}
    	return dp[l-1];
    }
}

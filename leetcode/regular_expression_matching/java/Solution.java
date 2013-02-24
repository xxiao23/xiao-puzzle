
public class Solution {
    public boolean isMatch(String s, String p) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    	
    	dp[0][0] = true;
    	for (int i = 1; i <= s.length(); ++i) {
    		dp[i][0] = false;
    	}
    	
    	for (int j = 1; j <= p.length(); ++j) {
    		char pc = p.charAt(j-1);
    		char prevPc = (j-2 >= 0) ? p.charAt(j-2) :'\0';
    		if (pc == '*' && prevPc != '\0') {
    			dp[0][j] = dp[0][j-2];
    		} else {
    			dp[0][j] = false;
    		}
    	}
    	
    	for (int i = 1; i <= s.length(); ++i) {
    		for (int j = 1; j <= p.length(); ++j) {
    			char sc = s.charAt(i-1);
    			char pc = p.charAt(j-1);
        		char prevPc = (j-2 >= 0) ? p.charAt(j-2) :'\0';
    			if (pc == '*' && prevPc != '\0') {
    				if (sc == prevPc || prevPc == '.') {
    					// a bit tricky here
    					// when a* matches a
    					// * can be 0, 1, or >1 of a
    					// thus, 3 cases all need to be considered.
    					dp[i][j] = dp[i-1][j-2] || dp[i-1][j] || dp[i][j-2];
    				} else {
    					dp[i][j] = dp[i][j-2];
    				}
    			} else if (sc == pc || pc == '.') {
    				// match 1 char
    				dp[i][j] = dp[i-1][j-1];
    			} else {
    				dp[i][j] = false;
    			}
    		}
    	}
    	return dp[s.length()][p.length()];
    }
}

public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null) return false;
        int n = s.length();
        boolean[] dp = new boolean[n+1]; // DP table, dp[i] = wordBreak(s[0..i])
        dp[0] = true; // the initial condition for the DP
        
        for (int i = 1; i <= n; i++) {
            // look for the last word that's in the dictionary
            dp[i] = false;
            for (int j = i; j > 0; j--) {
                if (dp[j-1] && dict.contains(s.substring(j-1, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
}

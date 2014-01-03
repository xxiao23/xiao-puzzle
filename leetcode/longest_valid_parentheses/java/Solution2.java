public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null) return 0;
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n <= 1) return 0;
        // DP array
        // dp[i] = the length of the longest valid parentheses starting at s[i]
        int[] dp = new int[n];
        dp[n-1] = 0;
        int ret = 0;
        for (int i = n-2; i >= 0; --i) {
            dp[i] = 0;
            // if s[i] is (
            // no way there is a valid parentheses starting at s[i]
            if (arr[i] == ')') continue;
            // now check the next index
            // right after the v.l.p starting at s[i+1]
            int j = i+1+dp[i+1];
            // the only way dp[i] can extend dp[i+1]
            // is that s[i] = ( and s[j] = )
            // so they match
            if (j >= n || arr[j] == '(') continue;
            dp[i] = dp[i+1]+2;
            // if dp[i] can be extended from dp[i+1]
            // check if dp[i] can be extendend further right
            if (j+1 < n) dp[i] += dp[j+1];
            ret = Math.max(ret, dp[i]);
        }
        
        return ret;
    }
}

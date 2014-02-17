public class Solution {
    public String longestPalindrome(String s) {
        // O(n) solution
        if (s == null) return null;
        if (s.length() == 0) return "";
        
        // preprocess the string s
        char[] t = preprocess(s).toCharArray();
        int n = t.length;
        
        // compute lengths of LPS centered at each character
        int C = 0; // LPS center
        int R = 0; // LPS right most index
        int[] P = new int[n]; // length of LPS centered at t[i];
        P[0] = 0;
        for (int i = 1; i < n-1; i++) {
            P[i] = (R < i) ? 0 : Math.min(P[2*C-i], R-i);
            while (t[i-1-P[i]] == t[i+1+P[i]]) P[i]++;
            if (i+P[i] > R) {
                C = i;
                R = i+P[i];
            }
        }
        
        // scan P[] to get the LPS overall
        int maxCenter = 0, maxLen = 0;
        for (int i = 1; i < n-1; i++) {
            if (maxLen < P[i]) {
                maxCenter = i;
                maxLen = P[i];
            }
        }
        int start = (maxCenter - maxLen - 1) / 2; // the start of LPS in the original string
        return s.substring(start, start+maxLen);
    }
    
    // convert ABA
    // to ^#A#B#A#$
    private String preprocess(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("^");
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append("#");
            sb.append(chars[i]);
        }
        sb.append("#$");
        return sb.toString();
    }
}

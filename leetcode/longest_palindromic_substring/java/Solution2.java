public class Solution {
    public String longestPalindrome(String s) {
        if (s == null) return null;
        char[] sa = s.toCharArray();
        int start = 0, end = 0, n = sa.length;
        int maxL = 0, maxStart = -1;
        for (int i = 0; i < n; i++) {
            // expand from s[i]
            start = i;
            end = i;
            while (start >= 0 && end < n && sa[start] == sa[end]) {
                start--;
                end++;
            }
            if (maxL < end-start-1) {
                maxL = end-start-1;
                maxStart = start+1;
            }
            // expand from s[i] and s[i+1]
            start = i;
            end = i+1;
            while (start >= 0 && end < n && sa[start] == sa[end]) {
                start--;
                end++;
            }
            if (maxL < end-start-1) {
                maxL = end-start-1;
                maxStart = start+1;
            }
        }
        return s.substring(maxStart, maxStart+maxL);
    }
}

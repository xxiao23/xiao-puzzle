public class Solution {
    public int numDecodings(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] t = new int[n+1];
        t[0] = 1;
        char c = s.charAt(0);
        if (c <= '0' || c > '9') return 0;
        t[1] = 1;
        
        for (int i = 2; i <= n; ++i) {
            c = s.charAt(i-1);
            t[i] = 0;
            if (c > '0' && c <= '9') {
                t[i] = t[i-1];
            }
            c = s.charAt(i-2);
            if (c == '0') continue;
            int k = Integer.valueOf(s.substring(i-2, i));
            if (k <= 26) t[i] += t[i-2];
        }
        return t[n];
    }
}

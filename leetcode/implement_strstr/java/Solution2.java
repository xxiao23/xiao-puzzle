public class Solution {
    public String strStr(String haystack, String needle) {
        // brutal force
        if (haystack == null || needle == null) return null;
        
        char[] ha = haystack.toCharArray();
        int n = ha.length;
        char[] na = needle.toCharArray();
        int m = na.length;
        if (n == 0 && m == 0) return "";
        for (int i = 0; i+m <= n; i++) {
            // try matching starting from index i
            int j = 0;
            for (; j < m; j++) {
                if (ha[i+j] != na[j]) break;
            }
            if (j == m) return haystack.substring(i);
        }
        return null;
    }
}

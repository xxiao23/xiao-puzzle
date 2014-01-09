public class Solution {
    public String convert(String s, int nRows) {
        if (s == null) return null;
        char[] sa = s.toCharArray();
        int n = sa.length;
        StringBuilder[] ss = new StringBuilder[nRows];
        for (int i = 0; i < nRows; i++) {
            ss[i] = new StringBuilder();
        }
        int i = 0, j = 0;
        while (i < n) {
            // zig
            for(j = 0; j < nRows && i < n; j++, i++) {
                ss[j].append(sa[i]);
            }
            if (i == n) break;
            // zag
            for (j = nRows-2; j > 0 && i < n; j--, i++) {
                ss[j].append(sa[i]);
            }
            if (i == n) break;
        }
        // construct the returned string
        StringBuilder ret = new StringBuilder();
        for (StringBuilder t : ss) {
            ret.append(t);
        }
        return ret.toString();
    }
}

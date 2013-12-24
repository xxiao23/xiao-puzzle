public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null) return false;
        if (p == null) return false;
        int n = s.length();
        int m = p.length();
        int ss = -1, star = -1, i = 0, j = 0;
        while (i < n) {
            char sc = s.charAt(i);
            char pc = j < m ? p.charAt(j) : '\0';
            if (pc == '?' || sc == pc) {
                // 1 on 1 match
                i++;
                j++;
                continue;
            } 
            if (pc == '*') {
                // 1 or more potential match
                star = j++;
                ss = i;
                continue;
            }
            if (star > -1) {
                j = star+1; // start from the next char to *
                i = ++ss; // let * match 1 more char in s
                continue;
            }
            return false;
        }
        // consume any stars immediately followed
        while (j < m) {
            if (p.charAt(j) == '*') j++;
            else return false;
        }
        
        return j == m;
    }
}

public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null) return false;
        if (p == null) return false;
        char[] sa = s.toCharArray();
        char[] pa = p.toCharArray();
        int n = sa.length, m = pa.length;
        int[][] c = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                c[i][j] = 0;
            }
        }
        return solver(sa, n, 0, pa, m, 0, c);
    }
    
    private boolean solver(char[] s, int n, int i, char[] p, int m, int j, int[][] c) {
        // base case
        if (j >= m) return i >= n;

        // memorization
        if (i < n && j < m) { 
        	if (c[i][j] == 1) return true;
            if (c[i][j] == -1) return false;
        }
        
        boolean ret = false;
        // the second character in p is not *
        if (j+1 >= m || p[j+1] != '*') { 
            if (i >= n || (p[j] != '.' && s[i] != p[j])) ret = false;
            else ret = solver(s, n, i+1, p, m, j+1, c);
        }
        // the second character in p is *
        else {
            // 3 cases
            // match 0 s[i]
        	ret = solver(s, n, i, p, m, j+2, c);
            // match 1 s[i]
            // match 2+ s[i]
        	// only do these if s is not exhausted, otherwise inf loop
        	if (i < n && !ret && (s[i] == p[j] || p[j] == '.')) {
        		ret = solver(s, n, i+1, p, m, j+2, c) || solver(s, n, i+1, p, m, j, c);
        	}
        }
        
        if (i < n && j < m) c[i][j] = ret ? 1 : -1;
        return ret;
    }
}

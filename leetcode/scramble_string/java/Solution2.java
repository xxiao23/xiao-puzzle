public class Solution2 {
    public boolean isScramble(String s1, String s2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        
        // do bottom-up DP
        // f(i, j, n) = true if s1[i..i+n-1] and s2[j..j+n-1] are scramble strings
        // f(i, j, n) = (s1[i..i+n-1] == s2[j..j+n-1])
        //              || {1<=k<n} (f(i, j, k) && f(i+k, j+k, n-k)) || (f(i, j+n-k, k) && f(i+n-k, j, n-k))
        
        // f(i, j, 0) = true
        int L = s1.length();
        boolean[][][] f = new boolean[L][L][L+1];
        for (int i = 0; i < L; ++i)
            for (int j = 0; j < L; ++j) 
                f[i][j][0] = true;
        
        for (int n = 1; n <= L; ++n) {
            for (int i = 0; i < L; ++i) {
                for (int j = 0; j < L; ++j) {
                    // either substring is out of bound
                    if (i+n > L || j+n > L) {
                        f[i][j][n] = false;
                        continue;
                    }
                    // check if the entire substring is identical
                    String ss1 = s1.substring(i, i+n);
                    String ss2 = s2.substring(j, j+n);
                    if (ss1.equals(ss2)) {
                        f[i][j][n] = true;
                        continue;
                    }
                    // do recurrence here
                    f[i][j][n] = false;
                    for (int k = 1; k <n; ++k) {
                        f[i][j][n] = f[i][j][n] || (f[i][j][k] && f[i+k][j+k][n-k]);
                        f[i][j][n] = f[i][j][n] || (f[i][j+n-k][k] && f[i+k][j][n-k]);
                    }
                }
            }
        }
        
        return f[0][0][L];
    }
}

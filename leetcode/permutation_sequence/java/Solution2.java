import java.util.*;

public class Solution2 {
    // compute the factorials from 0 to 9
    static int[] f = new int[10];
    static {
        f[0] = 1;
        for (int i = 1; i < 10; ++i) f[i] = i*f[i-1];
    }
    
    public String getPermutation(int n, int k) {
        if (k == 0) return "";
        
        StringBuilder s = new StringBuilder();
        BitSet used = new BitSet(n);
        int kk = k-1;
        // every step
        // drop in place the i_th digit
        // according to the sequence that each permutation appears in the global order
        for (int i = 0; i < n; ++i) {
            int t = kk/f[n-i-1];
            int l = -1;
            for (int j = 0; j < n; ++j) {
                if (!used.get(j)) l++;
                if (l == t) {
                    used.set(j);
                    s.append(Integer.valueOf(j+1).toString());
                    break;
                }
            }
            kk = kk % f[n-i-1];
        }
        
        return s.toString();
    }
}

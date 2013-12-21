import java.util.*;

public class Solution {
    public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> result = new ArrayList<String[]>();
        if (n == 0) return result;
        
        int[] r = new int[n]; // queen's position in each row
        for (int i = 0; i < n; ++i) r[i] = -1;
        BitSet c = new BitSet(n); // if there is a queen in a column
        
        worker(r, c, n, n, 0, result);
        return result;
    }
    
    // place a queen at 1 row at a time
    // since each row must have 1 and only 1 queen
    private void worker(int[] r, BitSet c, int k, int n, int i, ArrayList<String[]> result) {
        if (k == 0) {
            // return the board configuration
            String[] x = new String[n];
            for (int l = 0; l < n; ++l) {
                StringBuilder sb = new StringBuilder();
                for (int t = 0; t < n; ++t) {
                    if (r[l] == t) sb.append('Q');
                    else sb.append('.');
                }
                x[l] = sb.toString();
            }
            result.add(x);
            return;
        }
        if (i == n) return;
        
        // try every position in the i_th row
        for (int j = 0; j < n; ++j) {
            // check column
            if (c.get(j)) continue;
            // check diagonals of each previous rows
            int l = 0;
            for (; l < i; ++l) {
                if (i+j == l+r[l]) break;
                if (i-j == l-r[l]) break;
            }
            if (l < i) continue;
            // OK to put a queen at i_th row and j_th column
            c.set(j);
            r[i] = j;
            worker(r, c, k-1, n, i+1, result);
            // undo things
            c.clear(j);
            r[i] = -1;
        }
        
    }
}

import java.util.*;

public class Solution {
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (n < k) return result;
        ArrayList<Integer> c = new ArrayList<Integer>();
        worker(result, c, n, k, 1);
        return result;
    }
    
    private void worker(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> c, int n, int k, int s) {
        if (k == 0) {
            // add c into result
            ArrayList<Integer> nc = new ArrayList<Integer>();
            nc.addAll(c);
            result.add(nc);
            return;
        }
        
        if (s > n) return;
        
        for (int i = s; i <= n; ++i) {
            c.add(i);
            worker(result, c, n, k-1, i+1);
            c.remove(c.size()-1);
        }
        
        return;
    }
}

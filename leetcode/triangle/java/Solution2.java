public class Solution {
    private Map<Integer, HashMap<Integer, Integer>> cache = null; // memorization cache
    
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if (triangle == null || triangle.size() == 0) return 0;
        cache = new HashMap<Integer, HashMap<Integer, Integer>>();
        return worker(triangle, 0, 0);
    }
    
    private int worker(ArrayList<ArrayList<Integer>> triangle, int row, int col) {
        if (cache.containsKey(row) && cache.get(row).containsKey(col)) return cache.get(row).get(col);
        int e = triangle.get(row).get(col);
        // base case
        if (row == triangle.size()-1) return e;

        // recursion
        int m1 = worker(triangle, row+1, col); // go down left
        int m2 = worker(triangle, row+1, col+1); // go down right

        int ret = m1 < m2 ? e+m1 : e+m2;
        if (!cache.containsKey(row)) {
            cache.put(row, new HashMap<Integer, Integer>());
        }
        cache.get(row).put(col, ret);
        return ret;
    }
}

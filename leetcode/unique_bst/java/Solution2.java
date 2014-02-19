public class Solution {
    public int numTrees(int n) {
        // catalan number
        if (n == 0) return 1;
        int prev = 1, ret = 0;
        for (int i = 1; i <= n; i++) {
            ret = (2*i-1)*2*prev/(i+1);
            prev = ret;
        }
        return ret;
    }
}

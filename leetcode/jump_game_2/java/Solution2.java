public class Solution {
    public int jump(int[] A) {
        int start = 0, end = 0, jump = 0;
        int n = A.length;
        if (n == 0) return 0;
        int ne = A[0];
        for (int i = 1; i < n; ++i) {
            if (i > end) {
                // reach the end of the current jump
                jump++;
                start = end+1;
                end = ne;
            } 
            ne = Math.max(ne, i+A[i]);
        }
        
        return jump;
    }
}

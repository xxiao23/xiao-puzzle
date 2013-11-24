public class Solution {
    public int removeDuplicates(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int j = 0, i = 0;
        while (j < A.length) {
            int k = j+1, c = 2;
            while (k < A.length && A[j] == A[k]) { k++; }
            while (c-- > 0 && j != k) { A[i++] = A[j++]; }
            j = k;
        }
        return i;
    }
}

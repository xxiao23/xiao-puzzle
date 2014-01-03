public class Solution {
    public int removeElement(int[] A, int elem) {
        int n = A.length;
        int j = n-1; // the end of the array with elements removed
        int i = 0;
        while (i <= j) {
            if (A[i] == elem) {
                A[i] = A[j];
                j--;
            } else {
                i++;
            }
        }
        return j+1;
    }
}

public class Solution {
    public void nextPermutation(int[] num) {
        int n = num.length;
        if (n < 2) return;
        
        for (int i = n-2; i >= 0; --i) {
            // search backwards for the first pair
            // of adjacent elements that are increasing in values
            if (num[i] >= num[i+1]) continue;
            // now search from the end
            // the first element that's greater than num[i]
            int j = n-1;
            while (j >= 0 && num[j] <= num[i]) { j--; }
            // swap num[i] and num[j]
            swap(num, i, j); 
            // reverse from num[i+1] to the end
            reverse(num, i+1);
            return;
        }
        
        reverse(num, 0);
        return;
    }
    
    // swap elements at indice i and j
    private void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    
    // reverse the array from index k to the end
    private void reverse(int[] num, int k) {
        int n = num.length;
        int i = k, j = n-1;
        while (i < j) {
            swap(num, i, j);
            i++;
            j--;
        }
    }
}

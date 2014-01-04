public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        int n = num.length;
        Integer[] s = new Integer[3];
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; ++i) {
            if (i > 0 && num[i] == num[i-1]) continue; // skip repeating elements
            s[0] = num[i];
            twoSum(num, n, i+1, s, result, 0-s[0]);
        }
        return result;
    }
    
    private void twoSum(int[] num, int n, int k, Integer[] s, ArrayList<ArrayList<Integer>> result, int target) {
        int i = k, j = num.length-1;
        while (i < j) {
            if (num[i]+num[j] == target) {
                s[1] = num[i];
                s[2] = num[j];
                ArrayList<Integer> nl = new ArrayList<Integer>();
                nl.add(s[0]);
                nl.add(s[1]);
                nl.add(s[2]);
                result.add(nl);
                i++;
                while (i < n && num[i] == num[i-1]) i++;
                j--;
                while (j >= 0 && num[j] == num[j+1]) j--;
            } else if (num[i]+num[j] < target) {
                i++;
            } else {
                j--;
            }
        }
    }
}

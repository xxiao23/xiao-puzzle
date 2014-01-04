public class Solution {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int n = num.length;
        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (i > 0 && num[i] == num[i-1]) continue; // skip repeating elements
            int tmp = twoSum(num, n, i+1, target-num[i]);
            if (Math.abs(ret) > Math.abs(target-tmp-num[i])) {
                ret = target-tmp-num[i];
            }
        }
        return target-ret;
    }
    
    private int twoSum(int[] num, int n, int k, int target) {
        int i = k, j = num.length-1;
        int ret = Integer.MAX_VALUE;
        while (i < j) {
            if (num[i]+num[j] == target) {
                return target;
            } else if (num[i]+num[j] < target) {
                if (Math.abs(ret) > Math.abs(target-num[i]-num[j])) {
                    ret = target-num[i]-num[j];
                }
                i++;
            } else {
                if(Math.abs(ret) > Math.abs(target-num[i]-num[j])) {
                    ret = target-num[i]-num[j];
                }
                j--;
            }
        }
        return target-ret;
    }
}

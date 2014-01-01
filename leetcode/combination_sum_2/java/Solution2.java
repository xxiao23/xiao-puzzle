public class Solution {
    private ArrayList<ArrayList<Integer>> result = null;

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        int sz = num.length; // size
        ArrayList<Integer> s = new ArrayList<Integer>();
        int sum = 0, n = 0;
        result = new ArrayList<ArrayList<Integer>>();
        worker(num, sz, target, sum, s, n);
        return result;
    }
    
    private void worker(int[] num, int sz, int target, int sum, ArrayList<Integer> s, int n) {
        if (sum == target) {
            ArrayList<Integer> ns = new ArrayList<Integer>();
            ns.addAll(s);
            result.add(ns);
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = n; i < sz; ++i) {
            if (i > n && num[i] == num[i-1]) continue;
            s.add(num[i]);
            worker(num, sz, target, sum+num[i], s, i+1);
            s.remove(s.size()-1);
        }
    }
}

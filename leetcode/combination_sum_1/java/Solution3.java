public class Solution {
    private ArrayList<ArrayList<Integer>> result = null;
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int sz = candidates.length; // size
        ArrayList<Integer> s = new ArrayList<Integer>();
        int sum = 0, n = 0;
        result = new ArrayList<ArrayList<Integer>>();
        worker(candidates, sz, target, sum, s, n);
        return result;
    }
    
    private void worker(int[] candidates, int sz, int target, int sum, ArrayList<Integer> s, int n) {
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
            s.add(candidates[i]);
            worker(candidates, sz, target, sum+candidates[i], s, i);
            s.remove(s.size()-1);
        }
    }
}

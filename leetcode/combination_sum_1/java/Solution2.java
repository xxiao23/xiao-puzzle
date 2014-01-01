public class Solution {
    private HashMap<Integer, HashMap<Integer, ArrayList<ArrayList<Integer>>>> c = null;
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int n = candidates.length;
        c = new HashMap<Integer, HashMap<Integer, ArrayList<ArrayList<Integer>>>>();
        return worker(candidates, n-1, target);
    }
    
    private ArrayList<ArrayList<Integer>> worker(int[] candidates, int k, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        if (target == 0) {
            ret.add(new ArrayList<Integer>());
            return ret; // successfully find a solution
        }
        if (k >= 0 && target <= 0) return ret;
        if (k < 0) return ret;
        
        if (c.containsKey(k) && c.get(k).containsKey(target)) return c.get(k).get(target);
        
        ArrayList<ArrayList<Integer>> r1 = worker(candidates, k-1, target); // excluding candidates[k]
        ArrayList<ArrayList<Integer>> r2 = worker(candidates, k, target-candidates[k]); // including candidates[k]
        if (r1 != null) ret.addAll(r1);
        if (r2 != null) {
            for (ArrayList<Integer> l : r2) {
                ArrayList<Integer> nl = new ArrayList<Integer>();
                nl.addAll(l);
                nl.add(candidates[k]);
                ret.add(nl);
            }
        }
        
        if (!c.containsKey(k)) c.put(k, new HashMap<Integer, ArrayList<ArrayList<Integer>>>());
        c.get(k).put(target, ret);
        return ret;
    }
}

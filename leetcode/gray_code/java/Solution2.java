public class Solution {
    public ArrayList<Integer> grayCode(int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> ret = new ArrayList<Integer>();
        // base case
        if (n == 0) {
            ret.add(0);
            return ret;
        }
        
        ArrayList<Integer> l = grayCode(n-1);
        // the numbers starting with 0
        for (int i = 0; i < l.size(); ++i) {
            ret.add(l.get(i));
        }
        // the numbers starting with 1
        int k = 1<<(n-1);
        for (int i = l.size()-1; i >= 0; --i) {
            ret.add(k+l.get(i));
        }
        
        return ret;
    }
}

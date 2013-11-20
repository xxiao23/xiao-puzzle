public class Solution {
    HashMap<Integer, HashMap<String, ArrayList<String>>> c = null;
    
    public ArrayList<String> restoreIpAddresses(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        c = new HashMap<Integer, HashMap<String, ArrayList<String>>>();
        return worker(s, 4);
    }
    
    
    private ArrayList<String> worker(String s, int n) {
        if (s == null || s.length() == 0) return new ArrayList<String>();
        if (c.containsKey(n) && c.get(n).containsKey(s)) return c.get(n).get(s);
        
        ArrayList<String> ret = new ArrayList<String>();
        if (n == 1) {
            if (s.length() > 3 || (s.charAt(0) == '0' && s.length() > 1)) return ret;
            Integer i = Integer.valueOf(s);
            if (i <= 255 && i >= 0) {
                ret.add(s);
                if (!c.containsKey(n)) {
                    c.put(n, new HashMap<String, ArrayList<String>>());
                }
                c.get(n).put(s, ret);
            }
            return ret;
        }
        
        int l = s.length();
        for (int i = 1; i <= 3 && i <= l; ++i) {
            String t = s.substring(0, i);
            int k = Integer.valueOf(t);
            if (k > 255) break;
            ArrayList<String> sl = worker(s.substring(i), n-1);
            for (String ns : sl) {
                ret.add(t+"."+ns);
            }
            if (k == 0) break;
        }
        
        if (!c.containsKey(n)) {
            c.put(n, new HashMap<String, ArrayList<String>>());
        }
        c.get(n).put(s, ret);
        
        return ret;
    }
    
}

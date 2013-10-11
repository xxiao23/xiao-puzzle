public class Solution {
    private Map<String, ArrayList<ArrayList<String>>> d = null;
    public ArrayList<ArrayList<String>> partition(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        d = new HashMap<String, ArrayList<ArrayList<String>>>();
        return worker(s);
    }
    
    private ArrayList<ArrayList<String>> worker(String s) {
        if (d.containsKey(s)) return d.get(s);
        int n = s.length();
        ArrayList<ArrayList<String>> ps = new ArrayList<ArrayList<String>>();
        if (isP(s)) {
            ArrayList<String> nl = new ArrayList<String>();
            nl.add(s);
            ps.add(nl);
        }
        for (int i = 0; i < n-1; i++) {
            String s1 = s.substring(0, i+1);
            if (isP(s1)) {
                ArrayList<ArrayList<String>> subPs = worker(s.substring(i+1));
                for (ArrayList<String> a1 : subPs) {
                    ArrayList<String> nl = new ArrayList<String>();
                    nl.add(s1);
                    nl.addAll(a1);
                    ps.add(nl);
                }
            }
        }
        d.put(s, ps);
        return ps;
    }
    
    boolean isP(String s) {
        if (s.length() == 0) return true;
        int i = 0, j = s.length()-1;
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}

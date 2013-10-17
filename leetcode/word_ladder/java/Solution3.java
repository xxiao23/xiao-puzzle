public class Solution {
    static private char[] az = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    
    public int ladderLength(String start, String end, HashSet<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String, HashSet<String>> adj = new HashMap<String, HashSet<String>>(); // an adjacent map
        // for each word, check its 25 possible transformations on each of the 3 possible positions
        // it keeps this part linear
        Iterator<String> it = dict.iterator();
        while (it.hasNext()) {
            String w = it.next();
            int l = w.length();
            adj.put(w, new HashSet<String>());
            for (int i = 0; i < l; ++i) {
                for (int j = 0; j < 26; ++j) {
                    if (w.charAt(i) == az[j]) continue; // except the original char
                    char[] cs = w.toCharArray();
                    cs[i] = az[j];
                    String nw = new String(cs);
                    if (dict.contains(nw)) {
                        adj.get(w).add(nw);
                    }
                }
            }
        }
        // process the transformations from start
        if (!dict.contains(start)) {
            int l = start.length();
            adj.put(start, new HashSet<String>());
            for (int i = 0; i < l; ++i) {
                for (int j = 0; j < 26; ++j) {
                    if (start.charAt(i) == az[j]) continue; // except the original char
                    char[] cs = start.toCharArray();
                    cs[i] = az[j];
                    String nw = new String(cs);
                    if (dict.contains(nw)) {
                        adj.get(start).add(nw);
                    }
                }
            }
        }
        // process the transformations to end
        if (!dict.contains(end)) {
            int l = end.length();
            for (int i = 0; i < l; ++i) {
                for (int j = 0; j < 26; ++j) {
                    if (end.charAt(i) == az[j]) continue; // except the original char
                    char[] cs = end.toCharArray();
                    cs[i] = az[j];
                    String nw = new String(cs);
                    if (dict.contains(nw)) {
                        adj.get(nw).add(end);
                    }
                }
            }
        }
        // BFS from start
        // as soon as you see end
        // return the number of hops away from start
        HashSet<String> v = new HashSet<String>();
        Queue<String> q = new LinkedList<String>();
        String sentinel = "0";
        q.add(start);
        q.add(sentinel);
        int hops = 0;
        while (q.size() > 1) {
            String w = q.remove();
            if (end.equals(w)) {
                return hops+1;
            }
            if (sentinel.equals(w)) {
                hops++;
                continue;
            }
            HashSet<String> n = adj.get(w);
            for (String ns : n) {
                if (!v.contains(ns)) {
                    v.add(ns);
                    q.add(ns);
                }
            }
            if (!q.isEmpty() && sentinel.equals(q.peek())) {
                q.add(sentinel);
            }
        }
        
        return 0;
    }
}

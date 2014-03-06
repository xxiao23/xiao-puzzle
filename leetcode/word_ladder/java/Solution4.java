public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        int hops = 1;
        int n = start.length();
        dict.add(end);
        Deque<String> q1 = new ArrayDeque<String>();
        Deque<String> q2 = new ArrayDeque<String>();
        HashSet<String> visited = new HashSet<String>();
        q1.add(start);
        while (!q1.isEmpty()) {
            String curStr = q1.removeFirst();
            if (curStr.equals(end)) return hops;
            char[] curChars = curStr.toCharArray();
            for (int i = 0; i < n; i++) {
                char original = curChars[i];
                for (char c = 'a'; c < 'z'; c++) {
                    if (c == original) continue; // skip the word itself
                    curChars[i] = c;
                    String ns = new String(curChars);
                    if (!visited.contains(ns) && dict.contains(ns)) {
                        q2.addLast(ns);
                        visited.add(ns);
                    }
                }
                curChars[i] = original;
            }
            if (q1.isEmpty()) {
                // one level is exhausted
                hops++;
                // swap q1 and q2
                q1 = q2;
                q2 = new ArrayDeque<String>();
            }
        }
        return 0;
    }
}

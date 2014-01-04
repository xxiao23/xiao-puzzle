public class Solution {
    private static HashMap<Integer, ArrayList<String>> m = new HashMap<Integer, ArrayList<String>>();
    public ArrayList<String> generateParenthesis(int n) {
        if (m.containsKey(n)) return m.get(n);
        ArrayList<String> ret = new ArrayList<String>();
        if (n == 0) {
            ret.add(""); // "" for n == 0
            return ret;
        }
        
        // always put a ( at position 0
        for (int i = 1; i < 2*n; i+=2) {
            // i is the position of ) matching the leftmost (
            ArrayList<String> sub1 = generateParenthesis(i/2);
            ArrayList<String> sub2 = generateParenthesis(n-1-i/2);
            // cross product sub1 and sub2
            for (String s1 : sub1) {
                for (String s2 : sub2) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("(");
                    sb.append(s1);
                    sb.append(")");
                    sb.append(s2);
                    ret.add(sb.toString());
                }
            }
        }
        m.put(n, ret);
        return ret;
    }
}

public class Solution {
    public String countAndSay(int n) {
        if (n < 0) return null;
        String s = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            char[] ca = s.toCharArray();
            int count = 1;
            char pc = ca[0]; // last char
            for (int j = 1; j < ca.length; ++j) {
                if (ca[j] == pc) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(pc);
                    pc = ca[j];
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(pc);
            s = sb.toString();
        }
        
        return s;
    }
}

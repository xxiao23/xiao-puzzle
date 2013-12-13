public class Solution2 {
    public String addBinary(String a, String b) {
        if (a == null || b == null || a.length() == 0 || b.length() == 0) return "";
        
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        char[] cs = new char[Math.max(as.length, bs.length)];
        
        int i = as.length-1, j = bs.length-1, k = cs.length-1;
        int r = 0; // add result for the current bit
        int co = 0; // carry over
        while (i >= 0 && j >= 0) {
            if (as[i] == '0' && bs[j] == '0') {
                r = 0;
            } else if (as[i] == '0' && bs[j] == '1') {
                r = 1;
            } else if (as[i] == '1' && bs[j] == '0') {
                r = 1;
            } else if (as[i] == '1' && bs[j] == '1') {
                r = 2;
            }
            r += co;
            if (r > 1) {
                r -= 2;
                co = 1;
            } else {
                co = 0;
            }
            if (r == 0) cs[k] = '0';
            else cs[k] = '1';
            
            i--;
            j--;
            k--;
        }
        
        while (i >= 0) {
            if (as[i] == '0') r = 0;
            else if (as[i] == '1') r = 1;
            r += co;
            if (r > 1) {
                r -= 2;
                co = 1;
            } else {
                co = 0;
            }
            if (r == 0) cs[k] = '0';
            else cs[k] = '1';
            i--;
            k--;
        }
        
        while (j >= 0) {
            if (bs[j] == '0') r = 0;
            else if (bs[j] == '1') r = 1;
            r += co;
            if (r > 1) {
                r -= 2;
                co = 1;
            } else {
                co = 0;
            }
            if (r == 0) cs[k] = '0';
            else cs[k] = '1';
            j--;
            k--;
        }
        
        if (co == 0) return String.valueOf(cs);
        else {
            char[] e = new char[cs.length+1];
            e[0] = '1';
            for (i = 0; i < cs.length; ++i) e[i+1] = cs[i];
            return String.valueOf(e);
        }
    }
}

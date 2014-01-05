public class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if (strs == null || strs.length == 0) return sb.toString();
        int i = 0;
        while (true) {
            if (strs[0].length() == i) break;
            char c = strs[0].charAt(i);
            int j = 1;
            for (; j < strs.length; ++j) {
                if (strs[j].length() == i) break;
                if (strs[j].charAt(i) != c) break;
            }
            if (j != strs.length) break;
            sb.append(c);
            i++;
        }
        
        return sb.toString();
    }
}

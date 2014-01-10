public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        // assume all small letters
        char[] sa = s.toCharArray();
        int n = sa.length;
        int[] p = new int[26];
        for (int i = 0; i < 26; i++) p[i] = -1;
        int ml = 1, l = 1, start = 0;
        p[sa[0]-'a'] = 0;
        for (int i = 1; i < n; i++) {
            int k = (int)(sa[i]-'a');
            if (p[k] >= start) {
                // the char appeared before
                // save the longest one encountered
                ml = Math.max(ml, l);
                // update start point and length
                l -= p[k]-start+1;
                start = p[k]+1;
            }
            l++;
            p[k] = i;
        }
        
        return Math.max(ml, l);
    }
}

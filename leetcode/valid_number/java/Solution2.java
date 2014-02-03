public class Solution {
    public boolean isNumber(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (s == null) return false;
        
        char[] chars = s.toCharArray();
        int n = chars.length;
        int split = -1, i = 0;
        while(i < n && chars[i] != 'e' && chars[i] != 'E') i++;
        if (i < n) split = i; // split the string at the first e or E
        
        if (split == -1) {
            // no e or E
            // treat the entire string as one part
            return worker(chars, 0, n);
        } else {
            // both parts before and after e|E
            // must be a numeric
            if (split-1 >= 0 && chars[split-1] == ' ') return false; // make sure no white space preceding e|E
            if (split+1 < n && chars[split+1] == ' ') return false; // make sure no white space following e|E
            if (s.indexOf('.', split) > 0) return false; // no dot after e|E
            return worker(chars, 0, split) && worker(chars, split+1, n);
        }
    }
    
    private boolean worker(char[] chars, int start, int end) {
        int i = start;
        while (i < end && chars[i] == ' ') i++;
        if (i == end) return false; // all white spaces
        // now start processing the meat
        // the first char must between 0 and 9 or . + -
        // . + - must be followed by 0-9
        int dotIndex = -1;
        if (chars[i] == '.' || chars[i] == '+' || chars[i] == '-') {
            if (chars[i] == '.') dotIndex = i;
            if (i+1 == end) return false;
        } else if ((chars[i] < '0' || chars[i] > '9') && chars[i] != '+' && chars[i] != '-')  return false;
        i++;
        
        // start processing the remaining character
        while (i < end && chars[i] != ' ') {
            if (chars[i] == '.') {
                if (dotIndex >= 0) return false;
                else dotIndex = i;
            } else if (chars[i] < '0' || chars[i] > '9') {
                return false;
            } else if (chars[i] == ' ') {
                break;
            }
            
            i++;
        }
        while (i < end && chars[i] == ' ') i++;
        if (i != end) return false;
        if (dotIndex >= 0) { 
            if (dotIndex+1 < end && chars[dotIndex+1] >= '0' && chars[dotIndex+1] <= '9')
                return true;
            if (dotIndex-1 >= start && chars[dotIndex-1] >= '0' && chars[dotIndex-1] <= '9') 
                return true;
            return false;
        }

        return true;
    }
}

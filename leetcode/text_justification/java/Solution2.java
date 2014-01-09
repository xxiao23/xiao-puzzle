public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        int c = 0; // the character count of the words in a line
        int start = 0; // the start index of the word in a line
        int i = 0; 
        int n = words.length;
        ArrayList<String> ret = new ArrayList<String>();
        while (i < n) {
            int t = c + words[i].length();
            if (start < i && t+i-start > L) { // the line conains at least 1 word if the word has more than L chars
                // it's time to cut off the words
                // [start .. i-1] make a line
                
                // process the words into a justified line
                String line = processLine(words, start, i-1, c, L);
                ret.add(line);
                
                // reset character count and start index
                start = i;
                c = words[i].length();
            } else {
                c = t;
            }
            i++;
        }
        // deal with the last line if any
        if (start < n) {
            StringBuilder sb = new StringBuilder();
            int s = L-c;
            for (int j = start; j < n-1; ++j, s--) {
                sb.append(words[j]);
                sb.append(" ");
            }
            sb.append(words[n-1]);
            while (s > 0) {
                sb.append(" ");
                s--;
            }
            ret.add(sb.toString());
        }
        return ret;
    }
    
    private String processLine(String[] words, int start, int end, int c, int L) {
        StringBuilder sb = new StringBuilder();
        int g = 0; // the number of spaces to use in the current step 
        int s = L-c; // the total number of spaces available
        for (int i = start; i < end; ++i) {
            g = s / (end-i);
            sb.append(words[i]);
            if (s > g*(end-i)) g += 1; // allocate as many spaces as allowed
            for (int j = 0; j < g; j++) sb.append(" ");
            s -= g;
        }
        // append the last word
        sb.append(words[end]);
        while (s-- > 0) sb.append(" ");
        return sb.toString();
    }
}

import java.util.ArrayList;


public class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<String> result = new ArrayList<String>();
    	if (words.length == 0) {
    		return result;
    	}
    	
    	int i = 0, start = 0;
		int r = L; // remaining space in current line
		int wordLen = 0; // length of words not including spaces
    	while (i < words.length+1) {
    		// try to pack in as many as words
    		// with total length + minimum spaces <= L
    		if (i < words.length && words[i].length() <= r) {
    			// pack in 1 more word
    			r -= (words[i].length() == r) ? r : (words[i].length()+1);
    			wordLen += words[i].length();
    			i++;
    		} else {
    			// cannot pack in more words in the line
    			// distribute the spaces
    			if (i > start+1 && i < words.length) {
    				// more than 1 word in the line
	    			int remainder = (L-wordLen)%(i-start-1);
	    			for (int k = start; k < i-1; ++k) {
	    				for (int kk = 0; kk < (L-wordLen)/(i-start-1); ++kk) {
	    					words[k] += " ";
	    				}
	    			}
	    			for (int k = start; k < i-1 && remainder > 0; ++k, --remainder) {
	    				words[k] += " ";
	    			}
	    			StringBuilder sb = new StringBuilder();
	    			for (int k = start; k < i; ++k) {
	    				sb.append(words[k]);
	    			}
	    			result.add(sb.toString());
    			} else {
    				// only 1 word in the line
    				// or this is the last line
    				// make it left justified
    				StringBuilder sb = new StringBuilder();
    				
    				for (int k = start; k < i-1; ++k) {
    					sb.append(words[k]);
    					sb.append(" ");
    				}
    				sb.append(words[i-1]);
    				for (int k = sb.length(); k < L; ++k) {
    					sb.append(" ");
    				}
    				result.add(sb.toString());
    				
    				// if last line
    				// break out the loop
        			if (i == words.length) {
        				break;
        			}
    			}
	    		
    			// reset some configurations
    			r = L;
    			wordLen = 0;
    			start = i;
    			
    		}
    	}
    	
    	return result;
    }
}

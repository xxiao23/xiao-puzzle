
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (s3.length() != s1.length() + s2.length()) {
    		return false;
    	}
    	if (s1.length() == 0) {
    		return s2.equals(s3);
    	}
    	if (s2.length() == 0) {
    		return s1.equals(s3);
    	}
    	
    	boolean[][] t = new boolean[s1.length()+1][s2.length()+1];
        for (int j = 0; j < s2.length()+1; ++j) {
        	t[0][j] = s3.substring(0, j).equals(s2.substring(0,j));
        }
        for (int i = 0; i < s1.length()+1; ++i) {
        	t[i][0] = s3.subSequence(0, i).equals(s1.substring(0,i));
        }
        
        for (int i = 1; i < s1.length()+1; ++i) {
        	for (int j = 1; j < s2.length()+1; ++j) {
        		boolean b1 = (s1.charAt(i-1) == s3.charAt(i+j-1)) && t[i-1][j];
        		boolean b2 = (s2.charAt(j-1) == s3.charAt(i+j-1)) && t[i][j-1];
        		t[i][j] = b1 || b2;
        	}
        }
        
        return t[s1.length()][s2.length()];
    }
    
}

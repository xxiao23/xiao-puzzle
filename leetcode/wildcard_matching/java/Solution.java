public class Solution {
	
	public boolean isMatch(String s, String p) {
		// sanity check
		// non-star chars in p
		// should be at most the length of s
		int count = 0;
		for (int i = 0; i < p.length(); ++i) {
			if (p.charAt(i) != '*') {
				count++;
			}
		}
		
		if (count > s.length()) {
			return false;
		}
		
		// DP for normal case
		boolean[][] m = new boolean[2][p.length()+1];
		
		int prev = 0, cur = 1;
		m[0][0] = true;
		for (int j = 1; j < p.length()+1; ++j) {
			if (p.charAt(j-1) == '*') {
				m[0][j] = m[0][j-1];
			} else {
				m[0][j] = false;
			}
		}
		
		for (int i = 1; i < s.length()+1; ++i) {
			m[cur][0] = false;
			for (int j = 1; j < p.length()+1; ++j) {
				char pc = p.charAt(j-1);
				char sc = s.charAt(i-1);
				if (pc == '?') {
					m[cur][j] = m[prev][j-1];
				} else if (pc == '*') {
					// * can match 1) 0 char 2) 1 char 3) >=2 chars
					m[cur][j] = m[cur][j-1] || m[prev][j-1] || m[prev][j];
				} else {
					m[cur][j] = (pc == sc) && m[prev][j-1];
				}
			}
			
			int tmp = cur;
			cur = prev;
			prev = tmp;
		}
		
		return m[prev][p.length()];
	}
}

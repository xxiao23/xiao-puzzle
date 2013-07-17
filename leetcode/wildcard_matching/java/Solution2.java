public class Solution2 {
	// a promising method
	// but seems to be incorrect
	// check the case s = "aac", p ="*ac"
	public boolean isMatch(String s, String p) {
		if (p.length() == 0 && s.length() >0) {
			return false;
		}
		
		int i = 0, j = 0, lastStar = -1;
		while (i < s.length() || j < p.length()) {
			// if s is exhausted
			if (i == s.length()) { 
				if (p.charAt(j) != '*') {
					return false;
				} else {
					j++;
					continue;
				}
			}

			// if p is exhausted
			if (j == p.length()) {
				if (lastStar == -1) {
					return false;
				} else if (lastStar == p.length()-1) {
					return true;
				} else {
					j = lastStar+1;
					continue;
				}
			}
			
			char sc = s.charAt(i);
			char pc = p.charAt(j);
			// look for the last star position
			if (pc == '*') {
				lastStar = j;
				j++;
				continue;
			}
			
			if (pc == '?' || pc == sc) {
				i++;
				j++;
			} else {
				if (lastStar > -1) {
					j = lastStar+1;
					i++;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}

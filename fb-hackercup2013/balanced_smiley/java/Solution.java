import java.io.BufferedReader;
import java.io.FileReader;

public class Solution {
	
	public void solution(String input) throws Exception{
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		int m = Integer.valueOf(line.trim());
		System.out.println(m + " test cases");
		line = in.readLine();
		boolean result = false;
		int ci = 1;
		while (line != null) {
			result = bs(line.trim());
			System.out.println("Case #" + (ci++) + ": " +
					(result ? "YES" : "NO"));
			line = in.readLine();
		}
	}
	
	private boolean bs(String line) {
		String[] sb = new String[line.length()];
		int sLength = 0;
		int i = 0;
		while (i < line.length()) {
			// throw away anything not ')' or '('
			if (line.charAt(i) == '(') {
				sb[sLength++] = "(";
				++i;
			} else if (line.charAt(i) == ')') {
				sb[sLength++] = ")";
				++i;
			} else {
				if (line.charAt(i) == ':' && i+1 < line.length()) {
					if (line.charAt(i+1) == '(') {
						sb[sLength++] = ":(";
					}
					if (line.charAt(i+1) == ')') {
						sb[sLength++] = ":)";
					}
					i += 2;
				} else {
					++i;
				}
			} 
		}
		// sb contains only '(' or ')'
		// check if sb if valid parentheses
		if (sLength == 0) {
			return true;
		}
		
		// DP
		boolean[][] t = new boolean[sLength+1][sLength];
		for (i = 0; i < sLength; ++i) {
			if (":(".equals(sb[i]) || ":)".equals(sb[i])) {
				t[i][i] = true;
			} else {
				t[i][i] = false;
			}
		}
		// i > j meaning empty string
		for (i = 0; i < sLength; ++i) {
			for (int j = i-1; j >= 0; --j) {
				t[i][j] = true;
			}
		}
		// last row is padding
		for (int j = 0; j < sLength; ++j) {
			t[sLength][j] = true;
		}
		
		// build DP table
		for (int l = 2; l <= sLength; ++l) {
			for (i = 0; i+l-1 < sLength; ++i) {
				int j = i+l-1;
				t[i][j] = false;
				if (":(".equals(sb[i])) {
					if (t[i+1][j]) {
						t[i][j] = true;
					} else {
						for (int k = i+1; k <= j; ++k) {
							if ((")".equals(sb[k]) || ":)".equals(sb[k])) 
								&& t[i+1][k-1] && t[k+1][j]) {
								t[i][j] = true;
								break;
							}
						}
					}
				} else if (":)".equals(sb[i])) {
					t[i][j] = t[i+1][j];
				} else if ("(".equals(sb[i])) {
					for (int k = i+1; k <= j; ++k) {
						if ((")".equals(sb[k]) || ":)".equals(sb[k])) 
							&& t[i+1][k-1] && t[k+1][j]) {
							t[i][j] = true;
							break;
						}
					}
				} else if (")".equals(sb[i])) {
					t[i][j] = false;
				}
			}
		}
		return t[0][sLength-1];
	}
}

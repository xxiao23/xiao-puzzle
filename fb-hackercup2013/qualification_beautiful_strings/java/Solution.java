import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class Solution {
	
	public void solution(String input) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		int m = Integer.valueOf(line.trim());
		System.out.println(m + " test cases");
		line = in.readLine();
		long result;
		int ci = 1;
		int[] counts = new int[26];
		while (line != null) {
			result = mb(line.toLowerCase(), counts);
			System.out.println("Case #" + (ci++) + ": " + result);
			line = in.readLine();
		}
	}
	
	private long mb(String line, int[] counts) {
		long result = 0;
		for (int i = 0; i < 26; ++i) {
			counts[i] = 0;
		}
		for (int i = 0; i < line.length(); ++i) {
			char c = line.charAt(i);
			if (c >= 'a' && c <= 'z') {
				counts[c-'a'] += 1;
			}
		}
		Arrays.sort(counts);
		for (int i = 25; i >= 0 && counts[i] > 0; --i) {
			result += (i+1)*counts[i];
		}
		return result;
	}
}

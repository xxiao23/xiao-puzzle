import java.io.BufferedReader;
import java.io.FileReader;
import java.util.BitSet;


public class Solution {
	public void solution(String input) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		long t = Long.valueOf(line.trim());
		System.out.println(t + " test cases");
		line = in.readLine();
		long ci = 1;
		while(line != null) {
			String[] tmp = line.trim().split(" ");
			long n = Long.valueOf(tmp[0]);
			int k = Integer.valueOf(tmp[1]);
			line = in.readLine();
			String[] tmp1 = line.trim().split(" ");
			long a = Long.valueOf(tmp1[0]);
			long b = Long.valueOf(tmp1[1]);
			long c = Long.valueOf(tmp1[2]);
			long r = Long.valueOf(tmp1[3]);
			long result = fm(n, k, a, b, c, r);
			System.out.println("Case #" + (ci++) + ": " + result);
			line = in.readLine();
		}
	}
	
	private long fm(long n, int k, long a, long b, long c, long r) {
		// circular array
		long[] m = new long[k];
		BitSet test = new BitSet(k);
		long s = 0; // array start
		
		m[0] = a;
		for (int i = 1; i < k; ++i) {
			m[i] = (b * m[i-1] + c) % r;
		}
		
		long t = n-k-1;
		long result = 0;
		for (; t >= 0; --t, s = (s+1) % k) {
			test.clear();
			for (int i = 0; i < k; ++i) {
				if (m[i] < k) {
					test.set((int) m[i]);
				}
			}
			
			if (test.cardinality() == k) {
				break;
			}
			
			int i = 0;
			for (i = 0; i < k; ++i) {
				if (!test.get(i)) {
					break;
				}
			}
			m[(int) (s%k)] = i;
			result = i;
		}
		
		if (t < 0) {
			return result;
		}
		else if (t % (k+1) == 0) {
			return k;
		} else {
			int q = (int) (t % (k+1)) - 1;
			return m[(int) ((s+q)%k)];
		}
	}
}

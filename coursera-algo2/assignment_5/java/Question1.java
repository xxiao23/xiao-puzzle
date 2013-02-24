import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Question1 {
	private Map<BitSet, Double[]> prevSubproblems = null;
	private Map<BitSet, Double[]> curSubproblems = null;
	private List<BitSet> powerSets = null;
	private int N = 0;
	
	public double tsp(String input) 
		throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		N = Integer.valueOf(line.trim());
		double[] xs = new double[N+1];
		double[] ys = new double[N+1];
		int i = 1;
		while (true) {
			line = in.readLine();
			if (line == null) {
				break;
			}
			String[] tmp2 = line.trim().split(" ");
			double xv = Double.valueOf(tmp2[0]);
			xs[i] = xv;
			double yv = Double.valueOf(tmp2[1]);
			ys[i] = yv;
			++i;
		}
		
		// compute distances among all pairs of vertices
		double[][] ds = new double[N+1][N+1];
		for (int k = 1; k <= N; ++k) {
			for (int j = k+1; j <= N; ++j) {
				ds[k][j] = Math.sqrt((xs[k] - xs[j])*(xs[k] - xs[j])
									 +(ys[k] - ys[j])*(ys[k] - ys[j]));
				ds[j][k] = ds[k][j];
			}
		}
		
		// DP
		
		// base case 
		// where the subset has 1 and another number
		curSubproblems = new HashMap<BitSet, Double[]>();
		powerSets = new ArrayList<BitSet>();
		for (int k = 2; k <= N; ++k) {
			BitSet s0 = new BitSet(N+1);
			s0.set(1);
			s0.set(k);
			powerSets.add(s0);
			Double[] ns0 = new Double[N+1];
			ns0[k] = ds[1][k];
			curSubproblems.put(s0, ns0);
		}
		
		// fill up DB table
		for (int m = 3; m <= N; ++m) {
			System.out.println("working on subset size " + m);
			prevSubproblems = curSubproblems;
			curSubproblems = new HashMap<BitSet, Double[]>();
			powerSets = generateNextPowerSet();
			for(BitSet s : powerSets) {
				Double[] ns = new Double[N+1];
				for (int j = 2; j <= N; ++j) {
					if (!s.get(j)) {
						continue;
					}
					BitSet ss = (BitSet) s.clone();
					ss.clear(j);
					double minL = Double.MAX_VALUE;
					for (int k = 2; k <= N; ++k) {
						if (k == j || prevSubproblems.get(ss)[k] == null) {
							continue;
						}
						minL = Math.min(minL, prevSubproblems.get(ss)[k]+ds[k][j]);
					}
					ns[j] = minL;
				}
				curSubproblems.put(s, ns);
			}
		}
		
		// scan the final result 
		// and add the last edge
		double result = Double.MAX_VALUE;
		Double[] ns = curSubproblems.values().iterator().next();
		for (int j = 2; j <= N; ++j) {
			result = Math.min(result, ns[j] + ds[j][1]);
		}
		
		return result;
	}
	
	// generate subsets with cardinality k
	// given subsets with cardinality k-1
	// the super set is {2,3,4,..,n}
	private List<BitSet> generateNextPowerSet() {
		List<BitSet> result = new ArrayList<BitSet>();
		if (prevSubproblems == null) {
			return result;
		}
		
		for (BitSet s : powerSets) {
			int m = s.length() - 1;
			for (int k = m+1; k <= N; ++k) {
				BitSet ss = (BitSet) s.clone();
				ss.set(k);
				result.add(ss);
			}
		}
		
		return result;
	}
}

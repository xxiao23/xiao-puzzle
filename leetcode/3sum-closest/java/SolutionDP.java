public class SolutionDP {
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		// sort the numbers
		Arrays.sort(num);

		// shift every number to become non-negative	
		int shift = Math.abs(num[0]);
		target += 4 * shift;
		for (int i : num) {
			num[i] += shift;
		}

		// we need 4 DP tables
		// from 1-sum to 4-sum
		int[][][] dpTables = new int[4][target+1][num.length];

		// compute 1-sum tables, trivial
		for (int s = 0; s < target+1; ++s) {
			for (int n = 0; n < num.length; ++n) {
				if (num[n] == s) {
					dpTables[0][s][n] = 1;
				} else {
					dpTables[0][s][n] = 0;
				}
			}
		}

		// build up k-sum tables from bottom up
		// T(S, n, k) = T(S-num[n], n-1, k-1) + T(S, n-1, k)
		for (int k = 1; k < 4; ++k) {
			for (int s = 0; s < target+1; ++s) {
				for (int n = 0; n < num.length; ++n) {
					int a, b;
					if (s-num[n] < 0 || n-1 < 0) {
						a = 0;
					} else {
						a = dpTables[k-1][s-num[n]][n-1];
					}
					if (n-1 < 0) {
						b = 0;
					} else {
						b = dpTables[k][s][n-1];
					}
					dpTables[k][s][n] = a + b;
				} // n
			} // s
		} // k

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

		// now construct the solutions from 4-sum DP table
		for (int n = 3; n < num.length; ++n) {
			if (dpTables[4][target][n] > 0) {
				LinkedList<Integer> suffix = new LinkedList<Integer>();
				result.addAll(constructSolution(dpTables, num, target, n, 4, shift, suffix));
			}
		}

		return result;
	}

	private ArrayList<ArrayList<Integer>> constructSolution(int[][][] dpTables, int[] num, int target, int end, int k, int shift, LinkedList<Integer> suffix) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (k >= 1) {
			for (int n = 0; n <= end; ++n) {
				if (dpTables[k-1][target][n] > 0) {
					LinkedList<Integer> newSuffix = new LinkedList<Integer>(suffix);
					newSuffix.addFirst(s-shift);
					result.addAll(constructSolution(dpTables, num, target-num[n], n-1, k-1, shift, newSuffix));
				}
			}
		} 
		return result;
	}
}
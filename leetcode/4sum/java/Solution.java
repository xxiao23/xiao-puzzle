import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        
        Set<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();
        if (num.length < 4) {
            return new ArrayList<ArrayList<Integer>>(result);
        }
        
        // sort the numbers
		Arrays.sort(num);

		// shift every number to become non-negative	
		int shift = Math.abs(num[0]);
		target += 4 * shift;
		if (target < 0) {
			// impossible case
			return new ArrayList<ArrayList<Integer>>();
		}
		for (int i = 0; i < num.length; ++i) {
			num[i] += shift;
		}

		// we need 4 DP tables
		// from 1-sum to 4-sum
		int[][][] dpTables = new int[4][target+1][num.length];

		// compute 1-sum tables, trivial
		for (int s = 0; s <= target; ++s) {
			for (int n = 0; n < num.length; ++n) {
				if (num[n] == s) {
					dpTables[0][s][n] = 1;
				}
				if (n > 0) {
					dpTables[0][s][n] += dpTables[0][s][n-1];
				}
			}
		}

		// build up k-sum tables from bottom up
		// T(S, n, k) = T(S-num[n], n-1, k-1) + T(S, n-1, k)
		for (int k = 1; k < 4; ++k) {
			for (int s = 0; s <= target; ++s) {
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

		// recover the original info
		for (int k = 0; k < 4; ++k) {
			for (int s = 0; s <= target; ++s) {
				for (int n = num.length-1; n > 0; --n) {
					dpTables[k][s][n] -= dpTables[k][s][n-1];
				} // n
			} // s
		} // k
		
		LinkedList<Integer> suffix = new LinkedList<Integer>();
		result.addAll(constructSolution(dpTables, num, target, num.length-1, 4, shift, suffix));
		return new ArrayList<ArrayList<Integer>>(result);
	}

	private ArrayList<ArrayList<Integer>> constructSolution(int[][][] dpTables, int[] num, int target, int end, int k, int shift, LinkedList<Integer> suffix) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		if (k >= 1 && target >= 0 && end >= k-1) {
			for (int n = end; n >= 0; --n) {
				if (dpTables[k-1][target][n] > 0) {
					LinkedList<Integer> newSuffix = new LinkedList<Integer>(suffix);
					newSuffix.addFirst(num[n]-shift);
					result.addAll(constructSolution(dpTables, num, target-num[n], n-1, k-1, shift, newSuffix));
				}
			}
		} else {
			result.add(new ArrayList<Integer>(suffix));
		}
		return result;
	}
}
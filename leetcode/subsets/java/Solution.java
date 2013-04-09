import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	ArrayList<ArrayList<Integer>> result = null;
	
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        // Start typing your Java solution below
        // DO NOT write main() function
		result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> cur = new ArrayList<Integer>();
		result.add(new ArrayList<Integer>()); // always include [ ]
		
		if (S.length == 0) {
			return result;
		}
		
		Arrays.sort(S); // sort first so we will have elements in subsets non-decreasing
		
		subSetsRecursive(S, cur, 0);
		return result;
    }
	
	@SuppressWarnings("unchecked")
	private void subSetsRecursive(int[] S, ArrayList<Integer> cur, int startIndex) {
		for (int i = startIndex; i < S.length; ++i) {
			cur.add(S[i]);
			result.add((ArrayList<Integer>) cur.clone());
			subSetsRecursive(S, cur, i+1);
			cur.remove(cur.size()-1);
		}
	}
}

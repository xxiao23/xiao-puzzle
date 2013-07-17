import java.util.ArrayList;
import java.util.HashMap;


public class Solution {
	public int[] twoSum(int[] numbers, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
		if (numbers.length == 0) {
			return null;
		}
		
        HashMap<Integer, ArrayList<Integer>> m = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < numbers.length; ++i) {
        	ArrayList<Integer> t = null;
        	if (m.containsKey(numbers[i]) == false) {
        		t = new ArrayList<Integer>();
        	} else {
        		t = m.get(numbers[i]);
        	}
        	t.add(i);
        	m.put(numbers[i], t);
        }
        
        int k = 0;
        for (int i = 0; i < numbers.length; ++i) {
        	k = target - numbers[i];
        	if (m.containsKey(k)) {
        		for (Integer ii : m.get(k)) {
        			if (ii != i) {
        				int[] result = new int[2];
        				result[0] = Math.min(i+1, ii+1);
        				result[1] = Math.max(i+1, ii+1);
        				return result;
        			}
        		}
        	}
        }
        
        return null;
    }
}

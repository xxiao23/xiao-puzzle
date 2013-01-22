import java.util.ArrayList;

public class Solution {
	
	// passed both small and large test cases
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
    	int min = 0;
    	if (triangle == null || triangle.size() == 0) {
    		return min;
    	}
    	
    	int n = triangle.size();
    	int[] t = new int[n];
    	t[0] = triangle.get(0).get(0);
    	for (int i = 1; i < n; ++i) {
    		for (int j = i; j >= 0; --j) {
    			int tmp = Integer.MAX_VALUE;
    			if (j <= i-1) {
    				tmp = Math.min(tmp, t[j]);
    			}
    			if (j-1 >= 0) {
    				tmp = Math.min(tmp, t[j-1]);
    			}
    			t[j] = tmp + triangle.get(i).get(j);
    		}
    	}
    	
    	min = Integer.MAX_VALUE;
    	for (int j = 0; j < n; ++j) {
    		min = Math.min(min, t[j]);
    	}
    	return min;
    }
    
    // failed large case
    public int minimumTotal1(ArrayList<ArrayList<Integer>> triangle) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int sum = 0;
    	if (triangle == null || triangle.size() == 0) {
    		return sum;
    	}
    	
    	int min = Integer.MAX_VALUE;
    	int numRows = triangle.size();
    	// stack[0] stores values
    	// stack[1] stores 
    	int[][] stack = new int[2][numRows];
    	int sTop = 0; 
    	
    	stack[0][sTop] = 0;
    	sum += triangle.get(0).get(0);
    	stack[1][sTop] = 0;
    	
    	while (sTop >= 0) {
    		
    		
    		// confront a leaf
    		if (sTop == numRows-1) {
    			min = Math.min(sum, min);
    			sum -= triangle.get(sTop).get(stack[0][sTop]);
    			--sTop;
    			continue;
    		}
    		
    		if (stack[1][sTop] == 0) {
    			// go left
    			stack[1][sTop] = 1;
    			sum += triangle.get(sTop+1).get(stack[0][sTop]);
    			stack[0][sTop+1] = stack[0][sTop];
    			++sTop;
    			stack[1][sTop] = 0;
    		} else if (stack[1][sTop] == 1) {
    			// go right
    			stack[1][sTop] = 2;
    			sum += triangle.get(sTop+1).get(stack[0][sTop]+1);
    			stack[0][sTop+1] = stack[0][sTop]+1;
    			++sTop;
    			stack[1][sTop] = 0;
    		} else if (stack[1][sTop] == 2) {
    			// back off
    			sum -= triangle.get(sTop).get(stack[0][sTop]);
    			--sTop;
    		}
    	}
    	return min;
    }
    
}

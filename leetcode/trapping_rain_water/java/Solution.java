
public class Solution {
    public int trap(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (A.length == 0) {
    		return 0;
    	}
    	
        return trapRecursive(A, 0, A.length-1);
    }
    
    public int trapRecursive(int[] A, int start, int end) {
    	if (end - start < 2) {
    		return 0;
    	}
    	
    	int result = 0;
    	// find the two largest elements
    	int max, maxIndex, secondMax, secondMaxIndex;
    	if (A[start] >= A[start+1]) {
    		max = A[start];
    		maxIndex = start;
    		secondMax = A[start+1];
    		secondMaxIndex = start+1;
    	} else {
    		max = A[start+1];
    		maxIndex = start+1;
    		secondMax = A[start];
    		secondMaxIndex = start;
    	}
    	for (int i = start+2; i <= end; ++i) {
    		if (A[i] >= max) {
    			secondMax = max;
    			secondMaxIndex = maxIndex;
    			max = A[i];
    			maxIndex = i;
    		} else if (A[i] < max && A[i] >= secondMax) {
    			secondMax = A[i];
    			secondMaxIndex = i;
    		}
    	}
    	// between max and second max
    	// water are trapped
    	int i = Math.min(maxIndex, secondMaxIndex);
    	int j = Math.max(maxIndex, secondMaxIndex);
    	for (int k = i; k <= j; ++k) {
    		result += (A[k] <= secondMax) ? secondMax-A[k] : 0;
    	}
    	// recurse on the other 2 parts
    	result += trapRecursive(A, start, i);
    	result += trapRecursive(A, j, end);
    	return result;
    }
    
    // better solution
    // linear run-time
    public int trapLinear(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[]left=new int[A.length];
        int[]right=new int[A.length];
        if(A.length==0) return 0;
        int max=0;
        for(int i =0;i<A.length;i++){
            max=Math.max(max,A[i]);
            left[i]=max;
        }
        max=0;
        for(int i =A.length-1;i>=0;i--){
            max=Math.max(max,A[i]);
            right[i]=max;
        }
        max=0;
        for(int i =0;i<A.length;i++)
            max+=Math.min(left[i],right[i])-A[i];
        return max;
    }
}

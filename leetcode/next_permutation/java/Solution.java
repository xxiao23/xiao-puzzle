
public class Solution {
    public void nextPermutation(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	// go backwards from the end
    	// as long as the number is going larger
    	int i = num.length-1;
    	for (; i > 0 && num[i] <= num[i-1]; --i) {}
    	
    	// make the descending subarray from i
    	// an ascending subarray
    	int l = i;
    	int r = num.length-1;
    	while (l <= r) {
    		int tmp = num[l];
    		num[l] = num[r];
    		num[r] = tmp;
    		++l;
    		--r;
    	}
    	
    	if (i == 0) {
    		return;
    	}
    	
    	// find the smallest num from i to num.length-1
    	// that is larger than num[i-1]
    	for(l = i; l < num.length && num[l] <= num[i-1]; ++l) {}
    	l = Math.min(l, num.length-1);
    	
    	// swap num[l] to num[i-1]
    	int tmp = num[l];
    	num[l] = num[i-1];
    	num[i-1] = tmp;
    }
}


public class Solution {
	 public int[] searchRange(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
	    if (A.length == 0) {
	    	return new int[]{-1, -1};
	    }
	    
	    return new int[]{findFirst(A, target),
	    		findLast(A, target)};
	 }
	 
	 private int findFirst(int[] A, int target) {
		 int l = 0;
		 int r = A.length-1;
		 int mid;
		 int result = A.length;
		 while (l <= r) {
			 mid = (l+r)/2;
			 if (A[mid] > target) {
				 r = mid-1;
			 } else if (A[mid] < target) {
				 l = mid+1;
			 } else {
				 result = Math.min(mid, result);
				 r = mid-1;
			 }
		 }
		 return (result == A.length) ? -1 : result;
	 }
	 
	 private int findLast(int[] A, int target) {
		 int l = 0;
		 int r = A.length-1;
		 int mid;
		 int result = -1;
		 while (l <= r) {
			 mid = (l+r)/2;
			 if (A[mid] > target) {
				 r = mid-1;
			 } else if (A[mid] < target) {
				 l = mid+1;
			 } else {
				 result = Math.max(mid, result);
				 l = mid+1;
			 }
		 }
		 return result;
	 }
}

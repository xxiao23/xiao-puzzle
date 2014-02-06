
public class Solution {
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		int k = (m+n)%2 == 0 ? (m+n)/2 : (m+n+1)/2;
		return findKth(A, m, 0, m-1, B, n, 0, n-1, k);
	}
	
	// find the kth order statistics
	// in the union of A[la..ra] and B[lb..rb]
	private double findKth(int A[], int m, int la, int ra, int B[], int n, int lb, int rb, int k) {
		if (la > ra) {
			if ((m+n)%2 == 1) return (double)B[lb+k-1];
			else return (double)(B[lb+k-1]+B[lb+k]) / 2.0;
		}
		if (lb > rb) {
			if ((m+n)%2 == 1) return (double)A[la+k-1];
			else return (double)(A[la+k-1]+A[la+k]) / 2.0;
 		}
		
		// invariant
		// i-la+j-lb=k-1
		int i = (int)((ra-la)*1.0/((ra-la+rb-lb)*1.0)*(k-1))+la;
		int j = k-1+la+lb-i;
		
		if (A[i] <= B[j] && (j-1 < lb || A[i] >= B[j-1])) {
			if ((m+n)%2==1) return (double)A[i];
			else return (double)(A[i] + ((i+1)<m ? Math.min(A[i+1], B[j]) : B[j])) / 2.0;
		}
		if (B[j] <= A[i] && (i-1 < la || B[j] >= A[i-1])) {
			if ((m+n)%2==1) return (double)B[j];
			else return (double)(B[j] + ((j+1)<n ? Math.min(B[j+1], A[i]) : A[i])) / 2.0;
		}
		
		// elimination
		if (A[i] > B[j]) {
			return findKth(A, m, la, i-1, B, n, j+1, rb, k-j+lb-1);
		} else {
			return findKth(A, m, i+1, ra, B, n, lb, j-1, k-i+la-1);
		}
		
	}
}

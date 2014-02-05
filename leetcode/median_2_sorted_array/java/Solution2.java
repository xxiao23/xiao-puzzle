
public class Solution {
	public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length, n = B.length;
        return search(A, m, 0, m-1, B, n, 0, n-1);
    }
	
	private double search(int A[], int m, int la, int ra, 
			int B[], int n, int lb, int rb) {
		if (la == ra && lb == rb) {
			if (A[la] < B[lb]) {
				// A[la] must be one of the medians
				return (double)(A[la]+((la+1)<m ? Math.min(A[la+1], B[lb]) : B[lb]))/2.0;
			} else {
				// B[lb] must be one of the medians
				return (double)(B[lb]+((lb+1)<n ? Math.min(B[lb+1], A[la]) : A[la]))/2.0;
			}
		}
		// if one of arrays are empty
		if (la > ra) {
			if ((rb-lb+1)%2 == 1) return (double)B[(lb+rb)/2];
			else return (double)(B[(lb+rb)/2]+B[(lb+rb)/2+1])/2.0;
		}
		
		if (lb > rb) {
			if ((ra-la+1)%2 == 1) return (double)A[(la+ra)/2];
			else return (double)(A[(la+ra)/2]+A[(la+ra)/2+1])/2.0;
		}
		
		int totalLen = ra-la+1+rb-lb+1; // total number of elements combined
		int k = (totalLen%2==0) ? totalLen/2 : totalLen/2+1; // the median must be kth order statistics, k>=1
		int i = -1, j = -1;
		// the invariant is (i-la)+(j-lb)=k-1
		if (ra-la < rb-lb) {
			i = (la+ra)/2;
			j = k-1+la+lb-i; 
		} else {
			j = (lb+rb)/2;
			i = k-1+la+lb-j;
		}
		// found the median
		if (A[i] <= B[j] && (j-1<lb || B[j-1] <= A[i])) {
			if ((m+n)%2 == 1) return (double)A[i];
			else return (double)(A[i]+ ((i+1)<m ? Math.min(A[i+1], B[j]) : B[j]))/2.0;
		}
		if ( B[j] <= A[i] && (i-1 < la || B[j] >= A[i-1])) {
			if ((m+n)%2 == 1) return (double)B[j];
			else return (double)(B[j]+ ((j+1)<n ? Math.min(B[j+1], A[i]) : A[i]))/2.0;
		}
		
		if (A[i] < B[j]) {
			int delta = Math.min(i-la+1, rb-j+1);
			return search(A, m, la+delta, ra, B, n, lb, rb-delta);
		} else {
			int delta = Math.min(j-lb+1, ra-i+1);
			return search(A, m, la, ra-delta, B, n, lb+delta, rb);
		}
	}
}

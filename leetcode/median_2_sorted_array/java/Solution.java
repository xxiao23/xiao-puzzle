import java.util.ArrayList;
import java.util.Arrays;


public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
        int la = 0, ra = A.length-1;
        int lb = 0, rb = B.length-1;
        int ma, mb, as, bs, shift;
        
        // every iteration
        // peel off either left of right
        // segment of equal length from each array
        while (ra - la >= 2   &&  rb - lb >= 2) {
            ma = (la+ra)/2;
            mb = (lb+rb)/2;
            as = (ra-la)/2;
            bs = (rb-lb)/2;
        	shift = (as <= bs) ? as : bs;
        	if (A[ma] <= B[mb]) {
        		la += shift;
        		rb -= shift;
        	} else {
        		lb += shift;
        		ra -= shift;
        	}
        }
        
        // use a merge here can be faster in some contant factor
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        for (int i = la; i <= ra; ++i) {
        	tmp.add(A[i]);
        }
        for (int i = lb; i <= rb; ++i) {
        	tmp.add(B[i]);
        }

        Integer[] tmpArr = tmp.toArray(new Integer[1]);
        Arrays.sort(tmpArr);
        if (tmpArr.length % 2 ==1 ) {
        	return (double)tmpArr[(tmpArr.length-1)/2];
        } else {
        	return ((double)tmpArr[(tmpArr.length-1)/2] + (double)tmpArr[(tmpArr.length+1)/2]) / 2;
        }
    }
}

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {
	public int jump(int[] A) {
        int end = 0;
        int[] minJump = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
        	minJump[i] = Integer.MAX_VALUE;
        }
        minJump[0] = 0;
        
        for (int i = 0; i < A.length; ++i) {
        	
        	for (int k = end; k <= i+A[i] && k < A.length; ++k) {
        		if (minJump[k] > minJump[i] + 1) {
        			minJump[k] = minJump[i] + 1;
        		}
        	}
        	if (end < i+A[i]) {
        		end = Math.min(A.length-1, i+A[i]);
        	}
        }
        
        return minJump[A.length-1];
	}
	
	public int jump1(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
		Queue<Integer> q = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		
		q.add(0);
		q.add(-1);
		int steps = 0;
		
		while (q.size() > 0) {
			int v = q.poll();
			if (v == A.length-1) {
				return steps;
			}
			if (v == -1) {
				steps += 1;
				continue;
			}
			int nextV = q.peek();
			for(int i = A[v]; i >0; --i) {
				if (i+v < A.length && !visited.contains(i+v)) {
					q.add(i+v);
					visited.add(i+v);
				}
			}
			if (nextV == -1) {
				q.add(-1);
			}
			
		}
		
		return steps;
    }
}
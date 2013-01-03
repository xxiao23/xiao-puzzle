import java.util.Deque;
import java.util.LinkedList;


public class Solution {
    public int maximalRectangle(char[][] matrix) {
    	int numRows = matrix.length;
    	if (numRows == 0) {
    		return 0;
    	}
    	
    	int numCols = matrix[0].length;
    	// histogram matrix
    	int[][] hm = new int[numRows][numCols];
    	for (int j = 0; j < numCols; ++j) {
    		int h = 0;
    		for (int i = numRows-1; i >= 0; --i) {
    			if (matrix[i][j] == '0') {
    				h = 0;
    			} else {
    				h += 1;
    			}
    			hm[i][j] = h;
    		}
    	}
    	
    	// for each row
    	// compute the max rectangle under those histograms
    	int maxRec = 0;
    	for (int k = 0; k < numRows; ++k) {
    		int rec = largestRectangleArea(hm[k]);
    		maxRec = Math.max(rec, maxRec);
    	}
    	
    	return maxRec;
    }
    
    private int largestRectangleArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (height.length == 0) {
        	return 0;
        }
        
    	int maxArea = 0;
        Deque<Integer> s = new LinkedList<Integer>();
        Deque<Integer> ws = new LinkedList<Integer>();
        s.push(height[0]);
        ws.push(1);
        
        for (int i = 1; i < height.length; ++i) {
        	int h = s.peek();
        	if (height[i] >= h) {
        		s.push(height[i]);
        		ws.push(1);
        	} else {
        		int myWidth = 1;
        		int prevWidth = 0;
        		while(s.size() > 0 && s.peek() > height[i]) {
        			int w = ws.pop();
        			myWidth += w;
        			int n = s.pop();
        			w += prevWidth;
        			prevWidth = w;
        			maxArea = Math.max(maxArea, n*w);
        		}
        		s.push(height[i]);
        		ws.push(myWidth);
        	}
        }
        
        // now left with an ascending array in the stack
        int prevWidth = 0;
        while (s.size() > 0) {
        	int n = s.pop();
        	int w = prevWidth + ws.pop();
        	prevWidth = w;
        	maxArea = Math.max(maxArea, n*w);
        }
        
        return maxArea;
    }
}

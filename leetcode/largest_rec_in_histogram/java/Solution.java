import java.util.Deque;
import java.util.LinkedList;


public class Solution {
    public int largestRectangleArea(int[] height) {
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
        			System.out.println("height " + n + ", area = " + n*w);
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
			System.out.println("height " + n + ", area = " + n*w);
        	maxArea = Math.max(maxArea, n*w);
        }
        
        return maxArea;
    }
}

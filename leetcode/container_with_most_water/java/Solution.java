public class Solution {
    public int maxArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	int maxArea = 0;
    	int f = 0, b = height.length - 1;
    	while (f <= b) {
    		int xDelta = b - f;
    		int minHeight;
    		if (height[f] < height[b]) {
    			minHeight = height[f];
    			++f;
    		} else {
    			minHeight = height[b];
    			--b;
    		}
    		int curArea = xDelta * minHeight;
    		if (curArea > maxArea) {
    			maxArea = curArea;
    		}
    	}
        return maxArea;
    }
}
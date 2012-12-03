public class Solution {
    public int maxProfit(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (prices.length == 0) {
    		return 0;
    	}
    	
    	// price delta array
        int[] deltaPrices = new int[prices.length];
        
        deltaPrices[0] = 0;
        for(int i = 1; i < prices.length; ++i) {
        	deltaPrices[i] = prices[i] - prices[i-1];
        }
        
        // now we need to compute max continuous subarray sum
        int maxProfit = 0;
        for (int i = 0; i < deltaPrices.length; ++i) {
        	if (deltaPrices[i] > 0) {
        		maxProfit += deltaPrices[i];
        	}
        }
        
        return maxProfit;
    }
}
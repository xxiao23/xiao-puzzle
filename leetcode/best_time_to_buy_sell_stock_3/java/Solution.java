
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
        int length = prices.length;
        for(int i = 1; i < prices.length; ++i) {
        	deltaPrices[i] = prices[i] - prices[i-1];
        }
        
    	return maxProfitWorker(deltaPrices, 0, length-1);
    }
    
    private int maxProfitWorker(int[] arr, int s, int t) {
    	if (s > t) {
    		return 0;
    	}
    	if (s == t) {
    		if (arr[s] > 0) {
    			return arr[s];
    		} else {
    			return 0;
    		}
    	}
    	
    	// result can be in 2 scenarios
    	// 1) one sum is the max consecutive sum
    	//    while the other sum lies in either 
    	//    of the two remaining parts
    	ConsecutiveSum cs = maxConsecutiveSum(arr, s , t);
    	if (cs.left == -1 || cs.right == -1) {
    		return 0;
    	}
    	ConsecutiveSum lCs = maxConsecutiveSum(arr, 0, cs.left-1);
    	ConsecutiveSum rCs = maxConsecutiveSum(arr, cs.right+1, t);
    	int result1 = cs.sum + (lCs.sum > rCs.sum ? lCs.sum : rCs.sum);
    	// 2) the two sums both lie in the max consecutive sum
    	// Caveat : the two sums must cover head and tail of 
    	//         the max consecutive sum

    	// negate arr[left..right]
    	int[] negArr = new int[cs.right-cs.left+1];
    	for (int i = cs.left; i <= cs.right; ++i) {
    		negArr[i-cs.left] = -arr[i];
    	}
    	ConsecutiveSum negSum = maxConsecutiveSum(negArr, 0, negArr.length-1);
    	int result2 = negSum.sum + cs.sum;
    	return result1 > result2 ? result1 : result2;
    }
    
    private ConsecutiveSum maxConsecutiveSum(int[] arr, int s, int t) {
    	if (s > t) {
    		return new ConsecutiveSum(0, -1, -1);
    	}
    	ConsecutiveSum max = new ConsecutiveSum(0, -1, -1);
    	int cur = 0;
    	int left = s;
    	for (int i = s; i <= t; ++i) {
    		cur += arr[i];
    		if (cur > max.sum) {
    			max.sum = cur;
    			max.left = left;
    			max.right = i; 
    		} else if (cur < 0) {
    			cur = 0;
    			left = i+1;
    		}
    	}
    	return max;
    }
    
    private static class ConsecutiveSum {
    	public int sum;
    	public int left;
    	public int right;
    	public ConsecutiveSum(int s , int l, int r) {
    		sum = s;
    		left = l;
    		right = r;
    	}
    	
		@Override
		public String toString() {
			return "[sum=" + sum + ", left=" + left + ", right="
					+ right + "]";
		}
    }
}

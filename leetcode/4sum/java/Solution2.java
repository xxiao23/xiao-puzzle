public class Solution {
	// n^3 
	// 2 levels of outer loops
	// inner most loop is doing 2-sum
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num);
		int n = num.length;
		for (int i = 0; i+3 < n; ++i) {
			if (i > 0 && num[i] == num[i-1]) continue;
			int a = num[i];
			for (int j = i+1; j+2 < n; ++j) {
				if (j > i+1 && num[j] == num[j-1]) continue;
				int b = num[j];
				twoSum(num, n, j+1, a, b, target, ret);
			}
		}
		return ret;
	}
	
	// do 2 sum for target-a-b
	// use num[] starting at index k
	private void twoSum(int[] num, int n, int k, int a, int b, int target, ArrayList<ArrayList<Integer>> ret) {
		int i = k, j = n-1;
		int sum2 = target-a-b;
		while (i < j) {
			int sum = num[i]+num[j];
			if (sum == sum2) {
				ArrayList<Integer> nl = new ArrayList<Integer>();
				nl.add(a);
				nl.add(b);
				nl.add(num[i]);
				nl.add(num[j]);
				ret.add(nl);
				i++;
				while (i < n && num[i] == num[i-1]) i++;
				j--;
				while (j >= 0 && num[j] == num[j+1]) j--;
			} else if (sum < sum2) {
				i++;
			} else {
				j--;
			}
		}
	}
}

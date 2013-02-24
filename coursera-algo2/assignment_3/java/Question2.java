import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Question2 {
	
	private static Map<Long, HashMap<Long, Long>> resultCache = 
			new HashMap<Long, HashMap<Long, Long>>();
	
	public static long solution(String input) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		String[] tmp1 = line.split(" ");
		int totalWeight = Integer.valueOf(tmp1[0]);
		int numOfItems = Integer.valueOf(tmp1[1]);
		Item[] items = new Item[numOfItems];
		line = in.readLine();
		int i = 0;
		while (line != null) {
			String[] tmp2 = line.split(" ");
			long v = Long.valueOf(tmp2[0]);
			long w = Long.valueOf(tmp2[1]);
			items[i++] = new Item(w, v);
			line = in.readLine();
		}
		
		return knapsackRecursive(items, numOfItems, totalWeight);
	}
	
	private static long knapsackRecursive(Item[] items, long n, long w) {
		if (n <= 0 || w <= 0) {
			return 0;
		}
		// check the cache
		if (resultCache.containsKey(n) && resultCache.get(n).containsKey(w)) {
			return resultCache.get(n).get(w);
		}
		
		long result = Math.max(knapsackRecursive(items, n-1, w),
						(w < items[(int)(n-1)].weight) ? 0 :
						knapsackRecursive(items, n-1, w-items[(int) (n-1)].weight)
							+ items[(int) (n-1)].value);
		if (!resultCache.containsKey(n)) {
			resultCache.put(n, new HashMap<Long, Long>());
		}
		resultCache.get(n).put(w, result);
		
		return result;
		
	}
	
	public static long solution2(String input) throws Exception {
		
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		String[] tmp1 = line.split(" ");
		int totalWeight = Integer.valueOf(tmp1[0]);
		int numOfItems = Integer.valueOf(tmp1[1]);
		Item[] items = new Item[numOfItems];
		line = in.readLine();
		int i = 0;
		while (line != null) {
			String[] tmp2 = line.split(" ");
			long v = Long.valueOf(tmp2[0]);
			long w = Long.valueOf(tmp2[1]);
			items[i++] = new Item(w, v);
			line = in.readLine();
		}
		
		long[][] table = new long[2][totalWeight+1];
		int cur = 1;
		int prev = 0;
		for (i = 1; i <= numOfItems; ++i) {
			for (int j = 1; j <= totalWeight; ++j) {
				long quant1 = table[prev][j];
				long quant2 = (j < items[i-1].weight) ? 0
						: table[prev][(int) (j-items[i-1].weight)] + items[i-1].value;
				table[cur][j] = Math.max(quant1, quant2);
			}
			int tmp = cur;
			cur = prev;
			prev = tmp;
		}
		return table[prev][totalWeight];
	}
	
	private static class Item {
		public long weight;
		public long value;
		
		public Item(long w, long v) {
			weight = w;
			value = v;
		}
	}
}

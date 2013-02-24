import java.io.BufferedReader;
import java.io.FileReader;


public class Question1 {
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
		
		long[][] table = new long[numOfItems+1][totalWeight+1];
		for (i = 1; i <= numOfItems; ++i) {
			for (int j = 1; j <= totalWeight; ++j) {
				long quant1 = table[i-1][j];
				long quant2 = (j < items[i-1].weight) ? 0
						: table[i-1][(int) (j-items[i-1].weight)] + items[i-1].value;
				table[i][j] = Math.max(quant1, quant2);
			}
		}
		return table[numOfItems][totalWeight];
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

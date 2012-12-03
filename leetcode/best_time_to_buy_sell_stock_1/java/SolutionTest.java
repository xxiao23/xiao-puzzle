import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testMaxProfit() {
		int[] prices = {6,1,3,2,4,7};
		Solution solution = new Solution();
		int p = solution.maxProfit(prices);
		System.out.println(p);
		assertTrue(p >= 0);
	}

}

import static org.junit.Assert.*;

import org.junit.Test;


public class OptimalBstTest {
	OptimalBst s = new OptimalBst();
	@Test
	public void testOptimaBst() {
		double[] w1 = {0.6, 0.4};
		double result = s.optimaBst(w1);
		assertTrue("result = " + result, result == 1.4);
		
		double[] w2 = {0.4, 0.3, 0.3};
		result = s.optimaBst(w2);
		assertTrue("result = " + result, result == 1.7);
		
		double[] w3 = {0.2, 0.05, 0.17, 0.1, 0.2, 0.03, 0.25};
		result = s.optimaBst(w3);
		System.out.println(result);
	}

}

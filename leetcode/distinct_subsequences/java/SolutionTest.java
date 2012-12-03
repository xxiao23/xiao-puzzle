import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testNumDistinct() {
		String S = "AABB";
		String T = "AB";
		Solution solution = new Solution();
		int result = solution.numDistinct(S, T);
		System.out.println(result);
		assertTrue(result == 4);
	}

}

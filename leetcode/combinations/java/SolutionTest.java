import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testCombine() {
		int n = 4;
		int k = 2;
		Solution solution = new Solution();
		ArrayList<ArrayList<Integer>> result = solution.combine(n, k);
		System.out.println(result);
		assertNotNull(result);
	}

}

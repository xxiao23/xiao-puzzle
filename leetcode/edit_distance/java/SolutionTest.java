import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testMinDistance() {
		String word1 = "";
		String word2 = "";
		Solution solution = new Solution();
		int result = solution.minDistance(word1, word2);
		System.out.println(result);
		assertTrue(result == 0);
		
		word1 = "AAA";
		word2 = "AAB";
		result = solution.minDistance(word1, word2);
		System.out.println(result);
		assertTrue(result == 1);
	}
	
	@Test
	public void testMinDistance2() {
		String word1 = "ab";
		String word2 = "a";
		Solution solution = new Solution();
		int result = solution.minDistance(word1, word2);
		System.out.println(result);
		assertTrue(result == 1);
	}

}

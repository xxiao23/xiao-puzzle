import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testLetterCombinations1() {
		ArrayList<String> result = s.letterCombinations("23");
		System.out.println(result);
		assertTrue(result.size() == 9);
	}
	
	@Test
	public void testLetterCombinations2() {
		ArrayList<String> result = s.letterCombinations("21");
		System.out.println(result);
		assertTrue(result.size() == 3);
	}

}

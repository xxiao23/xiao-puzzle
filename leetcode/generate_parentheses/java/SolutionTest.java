import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void testGenerateParenthesis1() {
		int n = 1;
		ArrayList<String> result = s.generateParenthesis(n);
		System.out.println(result);
		assertTrue(result.size() == 1);
	}

	@Test
	public void testGenerateParenthesis2() {
		int n = 2;
		ArrayList<String> result = s.generateParenthesis(n);
		System.out.println(result);
		assertTrue(result.size() == 2);
	}
	
	@Test
	public void testGenerateParenthesis3() {
		int n = 3;
		ArrayList<String> result = s.generateParenthesis(n);
		System.out.println(result);
		assertTrue(result.size() == 5);
	}
}

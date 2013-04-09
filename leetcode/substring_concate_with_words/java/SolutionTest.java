import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		String S = "barfoothefoobarman";
		String[] L = {"foo", "bar"};
		ArrayList<Integer> result = s.findSubstring(S, L);
		System.out.println(result);
		assertTrue(result.get(0) == 0);
		assertTrue(result.get(1) == 9);
	}
	
	@Test
	public void test2() {
		String S= "foobarfoobarfoobar";
		String[] L = {"foo", "bar"};
		ArrayList<Integer> result = s.findSubstring(S, L);
		System.out.println(result);
		assertTrue(result.get(0) == 0);
		assertTrue(result.get(1) == 3);
	}

	@Test
	public void test3() {
		String S= "aaa";
		String[] L = {"a", "b"};
		ArrayList<Integer> result = s.findSubstring(S, L);
		System.out.println(result);
		assertTrue(result.size() == 0);
	}


}

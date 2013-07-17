import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		ArrayList<String> result = s.fullJustify(words, 16);
		System.out.println(result);
		assertTrue(result.size() == 3);
		assertTrue(result.get(0).length() == 16);
	}
	
	@Test
	public void test2() {
		String[] words = {"a","b","c","d","e"};
		ArrayList<String> result = s.fullJustify(words, 3);
		System.out.println(result);
		assertTrue(result.size() == 3);
		assertTrue(result.get(0).length() == 3);
	}
	
	@Test
	public void test3() {
		String[] words = {"What","must","be","shall","be."};
		ArrayList<String> result = s.fullJustify(words, 12);
		System.out.println(result);
		assertTrue(result.size() == 2);
		assertTrue(result.get(1).length() == 12);
	}

}

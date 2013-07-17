import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;


public class Solution2Test {

	Solution2 s = new Solution2();
	
	@Test
	public void test1() {
		String start = "hit";
		String end = "cog";
		HashSet<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dot");
		dict.add("lot");
		dict.add("log");
		dict.add("dog");
		int l = s.ladderLength(start, end, dict);
		System.out.println(l);
		assertTrue(l == 5);
	}
	
	@Test
	public void test2() {
		String start = "a";
		String end = "c";
		HashSet<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("b");
		dict.add("c");
		int l = s.ladderLength(start, end, dict);
		System.out.println(l);
		assertTrue(l == 2);
	}
	
	@Test
	public void test3() {
		String start = "hot";
		String end = "dog";
		HashSet<String> dict = new HashSet<String>();
		dict.add("hot");
		dict.add("dog");
		int l = s.ladderLength(start, end, dict);
		System.out.println(l);
		assertTrue(l == 0);
	}
}

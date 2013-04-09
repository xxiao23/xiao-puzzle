import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		String path = "/home/";
		String result = s.simplifyPath(path);
		assertTrue(result, "/home".equals(result));
	}
	
	@Test
	public void test2() {
		String path = "/a/./b/../../c/";
		String result = s.simplifyPath(path);
		assertTrue(result, "/c".equals(result));
	}
	
	@Test
	public void test3() {
		String path = "/../";
		String result = s.simplifyPath(path);
		assertTrue(result, "/".equals(result));
	}

	@Test
	public void test4() {
		String path = "/home//foo/";
		String result = s.simplifyPath(path);
		assertTrue(result, "/home/foo".equals(result));
	}
	
	@Test
	public void test5() {
		String path = "/.hidden";
		String result = s.simplifyPath(path);
		assertTrue(result, "/.hidden".equals(result));
	}
}

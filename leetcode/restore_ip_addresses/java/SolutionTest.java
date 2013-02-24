import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		ArrayList<String> result = s.restoreIpAddresses("25525511135");
		assertNotNull(result);
		System.out.println(result);
		assertTrue(result.size() == 2);
	}
	
	@Test
	public void test2() {
		ArrayList<String> result = s.restoreIpAddresses("1010");
		assertNotNull(result);
		System.out.println(result);
		assertTrue(result.size() == 1);
	}
	
	@Test
	public void test3() {
		ArrayList<String> result = s.restoreIpAddresses("10010");
		assertNotNull(result);
		System.out.println(result);
		assertTrue(result.size() == 2);
	}
}

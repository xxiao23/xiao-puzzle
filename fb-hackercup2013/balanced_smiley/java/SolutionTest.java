import org.junit.Test;

public class SolutionTest {
	String prefix = "/Users/xiang/Developer/eclipse-workspace/hackercup2013/balanced_smiley/src";
	String input0 = prefix + "/input0.txt";
	String input1 = prefix + "/input1.txt";
	String input2 = prefix + "/b_s.txt";
	Solution s = new Solution();

	@Test
	public void test1() throws Exception {
		s.solution(input0);
	}
	
	@Test
	public void test2() throws Exception {
		s.solution(input1);
	}

	@Test
	public void test3() throws Exception {
		s.solution(input2);
	}

}

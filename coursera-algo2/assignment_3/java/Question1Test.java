import static org.junit.Assert.*;

import org.junit.Test;


public class Question1Test {
	public String input1 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_3/src/knapsack1-tiny.txt";
	public String input2 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_3/src/knapsack1.txt";

	@Test
	public void testSolution1() throws Exception {
		long result = Question1.solution(input1);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
	
	@Test
	public void testSolution2() throws Exception {
		long result = Question1.solution(input2);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
}

import static org.junit.Assert.*;

import org.junit.Test;


public class Question2Test {

	public String input1 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_3/src/knapsack1-tiny.txt";
	public String input2 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_3/src/knapsack1.txt";
	public String input3 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_3/src/knapsack2.txt";

	@Test
	public void testSolution1() throws Exception {
		long result = Question2.solution(input1);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
	
	@Test
	public void testSolution2() throws Exception {
		long result = Question2.solution(input2);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
	
	@Test
	public void testSolution3() throws Exception {
		long result = Question2.solution2(input1);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
	
	@Test
	public void testSolution4() throws Exception {
		long result = Question2.solution2(input2);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}

	@Test
	public void testSolution5() throws Exception {
		long result = Question2.solution(input3);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
	
	@Test
	public void testSolution6() throws Exception {
		long result = Question2.solution2(input3);
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}
}

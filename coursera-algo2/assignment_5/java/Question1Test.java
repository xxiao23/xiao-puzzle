import static org.junit.Assert.*;

import org.junit.Test;


public class Question1Test {
	String prefix = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_5/src";
	String t1 = prefix + "/tsp-tiny.txt";
	String t2 = prefix + "/tsp.txt";
	Question1 q1 = new Question1();
	
	@Test
	public void testTsp1() throws Exception {
		double result = q1.tsp(t1);
		System.out.println("TSP = " + result);
		assertTrue(result > 0);
	}
	
	@Test
	public void testTsp2() throws Exception {
		double result = q1.tsp(t2);
		System.out.println("TSP = " + result);
		assertTrue(result > 0);
	}

}

import static org.junit.Assert.*;

import org.junit.Test;


public class Question1Test {
	public String input1 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering1.txt";
	public String input2 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering1-tiny.txt";
	public String input3 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering1-tiny2.txt";
	public String input4 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering1-tiny3.txt";

	@Test
	public void testMaxSpacingKClustering1() throws Exception {
		long result = Question1.maxSpacingKClustering(4, input1);
		System.out.println("max space = " + result);
		assertTrue(result >= Long.MIN_VALUE);
	}
	
	@Test
	public void testMaxSpacingKClustering2() throws Exception {
		long result = Question1.maxSpacingKClustering(4, input2);
		System.out.println("max space = " + result);
		assertTrue(result >= Long.MIN_VALUE);
	}
	
	@Test
	public void testMaxSpacingKClustering3() throws Exception {
		long result = Question1.maxSpacingKClustering(4, input3);
		System.out.println("max space = " + result);
		assertTrue(result >= Long.MIN_VALUE);
	}
	
	@Test
	public void testMaxSpacingKClustering4() throws Exception {
		long result = Question1.maxSpacingKClustering(4, input4);
		System.out.println("max space = " + result);
		assertTrue(result >= Long.MIN_VALUE);
	}
}

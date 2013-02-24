import static org.junit.Assert.*;

import org.junit.Test;


public class Question3Test {
	public String input1 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_1/src/edges.txt";
	public String input2 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_1/src/edges-tiny.txt";
	
	@Test
	public void testPrim1() throws Exception {
		long result = Question3.prim(input1);
		System.out.println("MST = " + result);
		assertTrue(result >= Long.MIN_VALUE);
	}
	
	@Test 
	public void testPrim2() throws Exception {
		long result = Question3.prim(input2);
		System.out.println("MST = " + result);
		assertTrue(result >= Long.MIN_VALUE);
	}

}

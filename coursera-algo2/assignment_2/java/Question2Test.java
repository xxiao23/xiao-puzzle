import static org.junit.Assert.*;

import org.junit.Test;


public class Question2Test {
	public String input1 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering2.txt";
	public String input2 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering2-tiny.txt";
	public String input3 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering2-tiny2.txt";
	public String input4 = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_2/src/clustering2-tiny3.txt";
	
	@Test
	public void testHammingDistanceMaxK1() throws Exception{
		int result = Question2.hammingDistanceMaxK(input1);
		System.out.println("max K = " + result);
		assertTrue(result >= 0);
	}
	
	@Test
	public void testHammingDistanceMaxK2() throws Exception {
		int result = Question2.hammingDistanceMaxK(input2);
		System.out.println("max K = " + result);
		assertTrue(result >= 0);
	}
	
	@Test
	public void testHammingDistanceMaxK3() throws Exception {
		int result = Question2.hammingDistanceMaxK(input3);
		System.out.println("max K = " + result);
		assertTrue(result >= 0);
	}
	
	@Test
	public void testHammingDistanceMaxK4() throws Exception {
		int result = Question2.hammingDistanceMaxK(input4);
		System.out.println("max K = " + result);
		assertTrue(result >= 0);
	}

}

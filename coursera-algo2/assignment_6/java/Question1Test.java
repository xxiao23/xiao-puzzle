import static org.junit.Assert.*;

import org.junit.Test;


public class Question1Test {
	String prefix = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_6/src";
	String toy0 = prefix + "/2sat-toy0.txt";
	String toy1 = prefix + "/2sat-toy1.txt";
	String toy2 = prefix + "/2sat-toy2.txt";
	
	String t0 = prefix + "/2sat0.txt";
	String t1 = prefix + "/2sat1.txt";
	String t2 = prefix + "/2sat2.txt";
	String t3 = prefix + "/2sat3.txt";
	String t4 = prefix + "/2sat4.txt";
	String t5 = prefix + "/2sat5.txt";
	String t6 = prefix + "/2sat6.txt";
	Question1 q1 = new Question1();
	
	@Test
	public void testSccToy0() throws Exception {
		boolean result = q1.scc(toy0);
		assertTrue(result);
	}
	
	@Test
	public void testSccToy1() throws Exception {
		boolean result = q1.scc(toy1);
		assertTrue(result);
	}
	
	@Test
	public void testSccToy2() throws Exception {
		boolean result = q1.scc(toy2);
		assertFalse(result);
	}
	
	@Test
	public void testScc0() throws Exception {
		boolean result = q1.scc(t0);
		assertTrue(result);
	}

}

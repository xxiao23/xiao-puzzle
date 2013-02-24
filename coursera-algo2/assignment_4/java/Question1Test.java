import org.junit.Test;


public class Question1Test {
	String prefix = "/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_4/src";
	String g1 = prefix + "/g1.txt";
	String g2 = prefix + "/g2.txt";
	String g3 = prefix + "/g3.txt";
	String g4 = prefix + "/g4.txt";
	String g5 = prefix + "/g5.txt";
	String g6 = prefix + "/g6.txt";
	
	@Test
	public void testAsapFloydWarshall1() 
			throws Exception {
		Question1.asapFloydWarshall(g1);
	}
	
	@Test
	public void testAsapFloydWarshall2() 
			throws Exception {
		Question1.asapFloydWarshall(g2);
	}
	
	@Test
	public void testAsapFloydWarshall3() 
			throws Exception {
		Question1.asapFloydWarshall(g3);
	}
	
	@Test
	public void testAsapFloydWarshall4() 
			throws Exception {
		Question1.asapFloydWarshall(g4);
	}
	
	@Test
	public void testAsapFloydWarshall5() 
			throws Exception {
		Question1.asapFloydWarshall(g5);
	}
	
	@Test
	public void testAsapFloydWarshall6() 
			throws Exception {
		Question1.asapFloydWarshall(g6);
	}

}

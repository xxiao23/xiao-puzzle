import static org.junit.Assert.*;

import org.junit.Test;


public class Question2Test {

	@Test
	public void testSolution() throws Exception {
		long result = Question2.solution();
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}

}

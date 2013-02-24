import static org.junit.Assert.*;

import org.junit.Test;


public class Question1Test {

	@Test
	public void testSolution() throws Exception {
		long result = Question1.solution();
		System.out.println("result = " + result);
		assertTrue(result > 0);
	}

}

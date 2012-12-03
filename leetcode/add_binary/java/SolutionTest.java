import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testAddBinary() {
		//"100", "110010"
		String a = "100";
		String b = "110010";
		String c = new Solution().addBinary(a, b);
		System.out.println(c);
		assertTrue("100".equalsIgnoreCase(c));
	}

}

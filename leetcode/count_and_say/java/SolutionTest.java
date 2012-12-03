import org.junit.Test;


public class SolutionTest {

	@Test
	public void testCountAndSay() {
		Solution solution = new Solution();
		String result = solution.countAndSay(1);
		System.out.println(result);
		result = solution.countAndSay(2);
		System.out.println(result);
		result = solution.countAndSay(3);
		System.out.println(result);
		result = solution.countAndSay(4);
		System.out.println(result);
	}

}

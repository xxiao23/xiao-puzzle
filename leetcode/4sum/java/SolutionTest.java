import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testFourSum() {
		int[] num = {-489,-487,-471,-464,-451,-421,-414,-405,-396,-355,-354,-350,-336,-335,-334,-307,-298,-296,-295,-287,-267,-256,-247,-231,-170,-130,-120,-109,-96,-80,-78,-71,-63,-56,-44,-43,-13,2,9,18,31,36,39,43,49,49,50,61,68,73,99,107,112,113,120,121,173,180,185,190,203,210,233,246,258,296,319,340,345,370,371,378,395,418,436,444,447,451,460,485};
		int target = 2926;
		Solution solution = new Solution();
		ArrayList<ArrayList<Integer>> result = solution.fourSum(num, target);
		assertNotNull(result);
		System.out.println(result);
	}

}

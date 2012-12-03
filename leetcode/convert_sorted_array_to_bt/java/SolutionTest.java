import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {

	@Test
	public void testSortedArrayToBST() {
		int[] num = {1, 2, 3, 4, 5};
		Solution solution = new Solution();
		TreeNode root = solution.sortedArrayToBST(num);
		System.out.println(root.val);
		assertNotNull(root);
	}

}

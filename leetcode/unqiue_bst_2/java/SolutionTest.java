import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		ArrayList<TreeNode> result = s.generateTrees(0);
		assertTrue(""+result.size(), result.size() == 1);
		result = s.generateTrees(1);
		assertTrue(""+result.size(), result.size() == 1);
		result = s.generateTrees(2);
		assertTrue(""+result.size(), result.size() == 2);
		result = s.generateTrees(3);
		assertTrue(""+result.size(), result.size() == 5);
	}

}

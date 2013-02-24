import static org.junit.Assert.*;

import org.junit.Test;


public class SolutionTest {
	Solution s = new Solution();
	
	@Test
	public void test1() {
		TreeLinkNode[] ns = new TreeLinkNode[7];
		for (int i = 0; i < 7; ++i) {
			ns[i] = new TreeLinkNode(i+1);
		}
		ns[0].left = ns[1];
		ns[0].right = ns[2];
		ns[1].left = ns[3];
		ns[1].right = ns[4];
		ns[2].left = ns[5];
		ns[2].right = ns[6];
		s.connect(ns[0]);
		assertNull(ns[0].next);
		assertTrue(ns[1].next.val == ns[2].val);
	}

}

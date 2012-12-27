import java.util.LinkedList;
import java.util.Deque;


public class Solution {
    public int longestValidParentheses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	Deque<Character> q = new LinkedList<Character>();
    	Deque<Integer> p = new LinkedList<Integer>();
    	int[] l = new int[s.length()];
    	for (int i = 0; i < s.length(); ++i) {
    		if (q.size() == 0) {
    			q.push(s.charAt(i));
    			p.push(i);
    			continue;
    		}
    		char c = q.peek();
    		char t = s.charAt(i);
    		if (c == '(' && t == ')') {
    			int m = p.pop();
    			l[m] = 1;
    			q.pop();
    			l[i] = 1;
    		} else {
    			q.push(t);
    			p.push(i);
    		}
    	}
    	
    	int curL = 0;
    	int maxL = 0;
    	for (int i = 0; i < l.length; ++i) {
    		if (l[i] == 0) {
    			curL = 0;
    		} else {
    			curL += 1;
    			if (curL > maxL) {
    				maxL = curL;
    			}
    		}
    	}
    	
        return maxL;
    }
}

import java.util.Stack;


public class Solution {
    public boolean isValid(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (s == null || s.length() == 0) {
    		return false;
    	}
    	
    	Stack<Character> st = new Stack<Character>();
    	st.push(s.charAt(0));
    	for (int i = 1; i < s.length(); ++i) {
    		if (st.empty()) {
    			st.push(s.charAt(i));
    			continue;
    		}
    		char c = st.peek();
    		if (c == '{') {
				if (s.charAt(i) == '}') {
					st.pop();
				} else {
					st.push(s.charAt(i));
				}
    		} else if (c == '(') {
    			if (s.charAt(i) == ')') {
    				st.pop();
    			} else {
    				st.push(s.charAt(i));
    			}
    		} else if (c == '[') {
    			if (s.charAt(i) == ']') {
    				st.pop();
    			} else {
    				st.push(s.charAt(i));
    			}
    		} else {
    			st.push(s.charAt(i));
    		}
    	}
    	
    	return st.size() == 0;
    }
}

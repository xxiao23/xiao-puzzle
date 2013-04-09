import java.util.Iterator;
import java.util.Stack;


public class Solution {
    public String simplifyPath(String path) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	if (path == null || "".equals(path)) {
    		return "";
    	}
    	path = "/" + path + "/";
    	Stack<String> ss = new Stack<String>();
    	ss.push("/");
    	int i = 1;
    	int strLen = path.length()-1;
    	while (i < strLen) {
    		char c = path.charAt(i);
			char nc = path.charAt(i+1);
    		if (c == '/') {
    			String prev = ss.peek();
    			if (!"/".equals(prev)) {
    				ss.push("/");
    			}
    			++i;
    		} else if (c == '.' &&
    				(nc == '.' || nc == '/')) {
    			if (nc == '.') {
    				if (ss.size() > 1) {
    					ss.pop();
    				}
    				if (ss.size() > 1) {
    					ss.pop();
    				}
    				i += 2;
    			} else if (nc == '/') {
    				if (ss.size() > 1) {
    					ss.pop();
    				}
    				i++;
    			}
    		} else {
    			int j = i;
    			while (j < strLen) {
    				if (path.charAt(j) != '/') {
    					j++;
    				} else {
    					break;
    				}
    			}
    			ss.push(path.substring(i, j));
    			i = j;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	Iterator<String> it = ss.iterator();
    	while (it.hasNext()) {
    		sb.append(it.next());
    	}
    	return sb.charAt(sb.length()-1) == '/' && sb.length() > 1 ? sb.toString().substring(0, sb.length()-1)
    										 : sb.toString();
    }
}

public class Solution2 {
    public String simplifyPath(String path) {
        LinkedList s = new LinkedList<String>(); // a linked list to contain final path components
        int pl = path.length();
        int i = 0;
        while (i < pl) {
            StringBuilder c = new StringBuilder();
            while (i < pl && path.charAt(i) != '/') {
                c.append(path.charAt(i));
                i++;
            }
            // deal with the current path compnent
            if (c.length() > 0) {
                String cs = c.toString();
                if ("..".equals(cs)) {
                    if (!s.isEmpty()) s.removeLast();
                } else if (!".".equals(cs)) {
                    s.add(cs);
                }
            }
            // deal with '/'
            if (i < pl) {
                i++;
            }
        }
        // rebuild the simplified path
        Iterator<String> it = s.iterator();
        StringBuilder sp = new StringBuilder();
        sp.append("/");
        while (it.hasNext()) {
            sp.append(it.next());
            if (it.hasNext()) sp.append("/");
        }
        return sp.toString();
    }
}

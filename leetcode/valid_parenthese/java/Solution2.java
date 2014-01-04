public class Solution {
    public boolean isValid(String s) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if (s == null) return true;
        
        char[] arr = s.toCharArray();
        int n = arr.length;
        Deque<Character> st = new ArrayDeque<Character>(); // deque used as a stack
        for (int i = 0; i < n; ++i) {
            char c = arr[i];
            if (c == '(' || c == '[' || c == '{') {
                st.addLast(c);
                continue;
            }
            if (st.isEmpty()) return false;
            char pc = st.removeLast();
            if (c == ')' && pc != '(') return false;
            if (c == ']' && pc != '[') return false;
            if (c == '}' && pc != '{') return false;
        }
        
        return st.isEmpty();
    }
}

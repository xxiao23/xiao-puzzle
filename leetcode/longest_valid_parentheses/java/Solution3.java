public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null) return 0;
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n <= 1) return 0;
        
        int ret = 0, last = -1; // last unmatched right parenthese
        // stack storing the unmatched left parenthese index
        Deque<Integer> st = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            if (arr[i] == '(') {
                st.addLast(i);
            } else {
                if (st.isEmpty()) {
                    // no ( to match the current )
                    last = i;
                } else {
                    // match 
                    int j = st.removeLast(); // get the index of the matching (
                    // now consider 2 cases
                    // 1) the stack is empty
                    //    the l.v.p can only be extended to the last unmatched )
                    // 2) the stack is NOT empty
                    //    the l.v.p can only be extended to the last unmatched (
                    if (st.isEmpty()) {
                        ret = Math.max(ret, i-last);
                    } else {
                        ret = Math.max(ret, i-st.peekLast());
                    }
                }
            }
        }
        return ret;
    }
}

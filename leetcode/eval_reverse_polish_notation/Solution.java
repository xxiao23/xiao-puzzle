public class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int op1 = 0, op2 = 0;
        for (String token : tokens) {
            // if the token is an operator
            // pop the top 2 elements
            // and perform the operation
            if (token.equals("+") || token.equals("-") || token.equals("/") || token.equals("*")) {
                op2 = stack.removeLast();
                op1 = stack.removeLast();
            }
            if (token.equals("+")) {
                stack.addLast(op1+op2);
            } else if (token.equals("-")) {
                stack.addLast(op1-op2);
            } else if (token.equals("/")) {
                stack.addLast(op1/op2);
            } else if (token.equals("*")) {
                stack.addLast(op1*op2);
            } else {
                stack.addLast(Integer.valueOf(token));
            }
        }
        
        return stack.removeLast();
    }
}

public class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        // now neither dividend nor divisor is 0
        long a = 0, b = 0, sign = 1;
        if (dividend > 0 && divisor > 0) {
            a = (long)dividend;
            b = (long)divisor;
            sign = 1;
        } else if (dividend < 0 && divisor < 0) {
            a = -(long)dividend;
            b = -(long)divisor;
            sign = 1;
        } else if (dividend > 0 && divisor < 0) {
            a = (long)dividend;
            b = -(long)divisor;
            sign = -1;
        } else {
            a = -(long)dividend;
            b = (long)divisor;
            sign = -1;
        }
        
        long bb = b;
        b = b<<31;
        while (b > a) { b = b>>1; }
        long c = 0; // quotient
        while (b >= bb) {
            c = c<<1;
            if (a >= b) {
                c += 1;
                a -= b;
            }
            b = b>>1;
        }
        
        if (sign > 0) return (int)c;
        else return (int)-c;
    }
}

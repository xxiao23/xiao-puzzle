
public class Solution {
    public int sqrt(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
        int res = 0;
        int bit = 1 << 30; // The second-to-top bit is set: 1L<<30 for long
     
        // "bit" starts at the highest power of four <= the argument.
        while (bit > x)
            bit >>= 2;
     
        while (bit != 0) {
            if (x >= res + bit) {
                x -= res + bit;
                res = (res >> 1) + bit;
            }
            else
                res >>= 1;
            bit >>= 2;
        }
        return res;
    }
}

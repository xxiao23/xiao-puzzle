public class Solution {
    static HashMap<Integer, Character> m = null;
    static HashMap<Integer, Integer[]> c = null;
    static {
        m = new HashMap<Integer, Character>();
    	c = new HashMap<Integer, Integer[]>();
        // numerals
        m.put(1, 'I');
        m.put(5, 'V');
        m.put(10, 'X');
        m.put(50, 'L');
        m.put(100, 'C');
        m.put(500, 'D');
        m.put(1000, 'M');
        // 1-9 
        c.put(1, new Integer[]{1});
        c.put(2, new Integer[]{1,1});
        c.put(3, new Integer[]{1,1,1});
        c.put(4, new Integer[]{1,5});
        c.put(5, new Integer[]{5});
        c.put(6, new Integer[]{5,1});
        c.put(7, new Integer[]{5,1,1});
        c.put(8, new Integer[]{5,1,1,1});
        c.put(9, new Integer[]{1,10});
    }
    
    public String intToRoman(int num) {
        int n = num;
        int d = 1000;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int k = n/d;
            if (k > 0 && k < 10) {
                Integer[] ca = c.get(k);
                for (Integer x : ca) {
                    sb.append(m.get(x*d));
                }
            }
            n = n%d;
            d = d/10;
        }
        return sb.toString();
    }
}

public class Solution {
    static private HashMap<Character, Character[]> kp = null; // phone key pad
    static {
        kp = new HashMap<Character, Character[]>();
        kp.put('2', new Character[]{'a', 'b', 'c'});
        kp.put('3', new Character[]{'d', 'e', 'f'});
        kp.put('4', new Character[]{'g', 'h', 'i'});
        kp.put('5', new Character[]{'j', 'k', 'l'});
        kp.put('6', new Character[]{'m', 'n', 'o'});
        kp.put('7', new Character[]{'p', 'q', 'r', 's'});
        kp.put('8', new Character[]{'t', 'u', 'v'});
        kp.put('9', new Character[]{'w', 'x', 'y', 'z'});
    }
    private ArrayList<String> result = null;
    public ArrayList<String> letterCombinations(String digits) {
        result = new ArrayList<String>();
        if (digits == null) return result;
        char[] da = digits.toCharArray();
        char[] comb = new char[da.length];
        worker(da, 0, comb);
        return result;
    }
    
    private void worker(char[] digits, int k, char[] comb) {
        if (k == digits.length) {
            result.add(new String(comb));
            return;
        }
        Character[] ca = kp.get(digits[k]);
        for (Character c : ca) {
            comb[k] = c;
            worker(digits, k+1, comb);
        }
    }
}

public class Solution {
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        // sort the numbers
        Arrays.sort(num);
        
        // iterate through the array
        int a=0, b, c;
        while (a < num.length) {
            int x = num[a];
            int r = 0 - x;
            // b traverse forward from a+1
            // c traverse backward from the end
            b = a + 1;
            c = num.length - 1;
            while (b < c) {
                if (x + num[b] + num[c] == 0) {
                    // find one solution
                    ArrayList<Integer> solution = new ArrayList<Integer>();
                    solution.add(Integer.valueOf(x));
                    solution.add(Integer.valueOf(num[b]));
                    solution.add(Integer.valueOf(num[c]));
                    result.add(solution);
                    // reset b and c 
                    ++b;
                    while (b < num.length && num[b-1] == num[b]) {
                        ++b;
                    }
                    --c;
                    while (c >= 0 && num[c+1] == num[c]) {
                        --c;
                    }
                } else if (x + num[b] + num[c] < 0) {
                    ++b;
                    while (b < num.length && num[b-1] == num[b]) {
                        ++b;
                    }
                } else {
                    --c;
                    while (c >= 0 && num[c+1] == num[c]) {
                        --c;
                    }
                }
            }
            ++a;
            while (a < num.length && num[a-1] == num[a]) {
                ++a;
            }
        }
        
        return result;
    }
}
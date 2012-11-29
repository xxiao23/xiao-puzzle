public class Solution {
    public int threeSumClosest(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
                
        // sort the numbers
        Arrays.sort(num);
        
        int length = num.length;
        if (length < 3) {
            return 0;
        }
        int closestSum = num[0] + num[1] + num[2];
        if (Math.abs(target - closestSum) > Math.abs(target - (num[length-1]+num[length-2]+num[length-3]))) {
            closestSum = num[length-1] + num[length-2] + num[length-3];
        }
        if (closestSum == target) {
            return target;
        }
        
        int delta = Math.abs(target - closestSum);
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
                if (x + num[b] + num[c] == target) {
                    // find a sum equal to target, return immediately
                    return target;
                } else if (x + num[b] + num[c] < target) {
                    int newDelta = Math.abs(target - x - num[b] - num[c]);
                    if (delta > newDelta) {
                        delta = newDelta;
                        closestSum = x + num[b] + num[c];
                    } 
                    ++b;
                    while (b < num.length && num[b-1] == num[b]) {
                        ++b;
                    }
                } else {
                    int newDelta = Math.abs(target - x - num[b] - num[c]);
                    if (delta > newDelta) {
                        delta = newDelta;
                        closestSum = x + num[b] + num[c];
                    }
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
        
        return closestSum;
    }
}
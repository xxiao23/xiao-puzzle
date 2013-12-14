public class Solution2 {
    public int[][] generateMatrix(int n) {
        int[][] m = new int[n][n];
        int left = 0, right = n-1;
        int top = 0, bottom = n-1;
        int k = 0, direction = 0; // 0 - 3
        while (left <= right && top <= bottom) {
            switch (direction) {
                case 0:
                    for (int j = left; j <= right; ++j) m[top][j] = ++k;
                    top++;
                    direction = 1;
                    break;
                case 1:
                    for (int i = top; i <= bottom; ++i) m[i][right] = ++k;
                    right--;
                    direction = 2;
                    break;
                case 2:
                    for (int j = right; j >= left; --j) m[bottom][j] = ++k;
                    bottom--;
                    direction = 3;
                    break;
                case 3:
                    for (int i = bottom; i >= top; --i) m[i][left] = ++k;
                    left++;
                    direction = 0;
                    break;
                default:
                    break;
            }
        }
        return m;
    }
}

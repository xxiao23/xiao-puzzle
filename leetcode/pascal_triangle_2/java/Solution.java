import java.util.ArrayList;


public class Solution {
    public ArrayList<Integer> getRow(int rowIndex) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	ArrayList<Integer> result = new ArrayList<Integer>();
    	
        int[][] rows = new int[2][rowIndex+1];
        int cur = 1;
        int last = 0;
        
        for (int i = 0; i <= rowIndex; ++i) {
        	int tmp = cur;
        	cur = last;
        	last = tmp;
        	int l = 0;
        	rows[cur][l++] = 1;
        	while (l < i) {
        		rows[cur][l] = rows[last][l-1] + rows[last][l];
        		++l;
        	}
        	rows[cur][i] = 1;
        }
        
        for (int i = 0; i <= rowIndex; ++i) {
        	result.add(rows[cur][i]);
        }
        
        return result;
    }
}

import java.util.ArrayList;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	Interval sentinel = new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE);
    	intervals.add(sentinel);
        ArrayList<Interval> result = new ArrayList<Interval>();

        int i = 0;
        for (; i < intervals.size(); i++) {
        	Interval v = intervals.get(i);
        	if (v.end < newInterval.start) {
        		// this interval has no overlap with new interval
        		result.add(v);
        	} else if (v.start > newInterval.end) {
        		result.add(newInterval);
        		break;
        	} else {
        		if (v.start < newInterval.start) {
        			newInterval.start = v.start;
        		}
        		if (v.end > newInterval.end) {
        			newInterval.end = v.end;
        		}
        	}
        }
        for (; i < intervals.size()-1; i++) {
        	result.add(intervals.get(i));
        }
        return result;
        
    }
}

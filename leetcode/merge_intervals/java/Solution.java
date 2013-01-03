import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


public class Solution {
	
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
    	
    	if (intervals.size() == 0) {
    		return new ArrayList<Interval>();
    	}
    	
    	// sort intervals by start and then by end
        Collections.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval arg0, Interval arg1) {
				if (arg0 == null && arg1 == null) {
					return 0;
				}
				if (arg0 == null) {
					return -1;
				}
				if (arg1 == null) {
					return 1;
				}
				
				if (arg0.start == arg1.start) {
					return arg0.end - arg1.end;
				}
				return arg0.start - arg1.start;
			}
        	
        });
        
        // scan and merge
        ArrayList<Interval> result = new ArrayList<Interval>();
        Interval in = intervals.get(0);
        Iterator<Interval> it = intervals.iterator();
        while (it.hasNext()) {
        	Interval curIn = it.next();
        	if (curIn.end < in.start || curIn.start > in.end) {
        		// no overlapping
        		result.add(in);
        		in = curIn;
        	} else {
        		in.start = Math.min(in.start, curIn.start);
        		in.end = Math.max(in.end, curIn.end);
        	}
        }
        if (in != null) {
        	result.add(in);
        }
        
        return result;
    }
}

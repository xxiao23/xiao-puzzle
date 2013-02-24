import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * In this programming problem and the next you'll code up the greedy algorithms from lecture for minimizing the weighted sum of completion times.. Download the text file here. This file describes a set of jobs with positive and integral weights and lengths. It has the format
[number_of_jobs]
[job_1_weight] [job_1_length]
[job_2_weight] [job_2_length]
...
For example, the third line of the file is "74 59", indicating that the second job has weight 74 and length 59. You should NOT assume that edge weights or lengths are distinct.

Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the difference (weight - length). Recall from lecture that this algorithm is not always optimal. IMPORTANT: if two jobs have equal difference (weight - length), you should schedule the job with higher weight first. Beware: if you break ties in a different way, you are likely to get the wrong answer. You should report the sum of weighted completion times of the resulting schedule --- a positive integer --- in the box below.

ADVICE: If you get the wrong answer, try out some small test cases to debug your algorithm (and post your test cases to the discussion forum)!

Answer for Question 1
69119377652
 * @author xiang
 *
 */
public class Question1 {
	public static long solution () throws Exception {
		long result = 0;
		BufferedReader in = new BufferedReader(new FileReader("/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_1/src/jobs.txt"));
		String line = in.readLine();
		int numOfJobs = Integer.valueOf(line);
		Job[] jobs = new Job[numOfJobs];
		int i = 0;
		line = in.readLine();
		while (line != null) {
			System.out.println(line);
			String[] tmp = line.split(" ");
			int weight = Integer.valueOf(tmp[0]);
			int length = Integer.valueOf(tmp[1]);
			jobs[i] = new Job(weight, length);
			i++;
			line = in.readLine();
		}
		
		Arrays.sort(jobs, new SortByDelta());
		int cumLength = 0;
		for(Job job : jobs) {
			cumLength += job.length;
			result += job.weight*cumLength;
		}
		return result;
	}
	
	public static class SortByDelta implements Comparator<Job> {

		@Override
		public int compare(Job arg0, Job arg1) {
			int delta0 = arg0.weight - arg0.length;
			int delta1 = arg1.weight - arg1.length;
			
			if (delta0 != delta1) {
				return delta1 - delta0;
			} else {
				return arg1.weight - arg0.weight; 
			}
		}
		
	}
}

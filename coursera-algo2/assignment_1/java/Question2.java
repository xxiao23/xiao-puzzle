import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * For this problem, use the same data set as in the previous problem. 
 * Your task now is to run the greedy algorithm that schedules jobs (optimally) in 
 * decreasing order of the ratio (weight/length). 
 * In this algorithm, it does not matter how you break ties. 
 * You should report the sum of weighted completion times of the resulting schedule 
 * --- a positive integer --- in the box below.
 * 
 * You entered:
 * 67311454237
 * @author xiang
 *
 */
public class Question2 {
	public static long solution () throws Exception {
		long result = 0;
		BufferedReader in = new BufferedReader(new FileReader(
				"/Users/xiang/Developer/eclipse-workspace/coursera-algo2/assignment_1/src/jobs.txt"));
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
		
		Arrays.sort(jobs, new SortByDivide());
		int cumLength = 0;
		for(Job job : jobs) {
			cumLength += job.length;
			result += job.weight*cumLength;
		}
		return result;
	}
	
	public static class SortByDivide implements Comparator<Job> {

		@Override
		public int compare(Job arg0, Job arg1) {
			int product1 = arg0.weight * arg1.length;
			int product2 = arg1.weight * arg0.length;
			
			if (product1 != product2) {
				return product2 - product1;
			} else {
				return arg1.weight - arg0.weight;
			}
		}
		
	}
}

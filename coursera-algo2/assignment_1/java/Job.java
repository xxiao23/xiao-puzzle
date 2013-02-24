
public class Job {
	public int weight;
	public int length;
	
	public Job(int w, int l) {
		this.weight = w;
		this.length = l;
	}
	
	public String toString() {
		return weight + " " + length;
	}
}

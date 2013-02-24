import java.util.HashMap;
import java.util.Map;

public class Vertex {
	long id;
	long score;
	Map<Vertex, Long> edgeWeights = new HashMap<Vertex, Long>();
	
	public Vertex(long id) {
		this.id = id;
		this.score = Long.MAX_VALUE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", score=" + score + "]";
	}
}

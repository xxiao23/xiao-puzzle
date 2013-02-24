import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class Question1 {
	public static void asapFloydWarshall(String input) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		String[] tmp1 = line.split(" ");
		int N = Integer.valueOf(tmp1[0]);
		Vertex[] V = new Vertex[N];
		line = in.readLine();
		// build the graph
		while (line != null) {
			String[] tmp2 = line.split(" ");
			int u = Integer.valueOf(tmp2[0]);
			int v = Integer.valueOf(tmp2[1]);
			int w = Integer.valueOf(tmp2[2]);
			if (V[u-1] == null) {
				V[u-1] = new Vertex(u-1);
			}
			if (V[v-1] == null) {
				V[v-1] = new Vertex(v-1);
			}
			if (!V[u-1].outs.containsKey(V[v-1])) {
				V[u-1].outs.put(V[v-1], w);
			}
			line = in.readLine();
		}
		
		// ASAP Floyd Warshall algorithm
		int T[][][] = new int[N][N][2];
		int cur = 0;
		int last = 1;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <N; ++j) {
				if (i == j) {
					T[i][j][0] = 0;
				} else if (V[i] != null && V[i].outs.containsKey(V[j])) {
					T[i][j][0] = V[i].outs.get(V[j]);
				} else {
					T[i][j][0] = Integer.MAX_VALUE;
				}
			}
		}
		
		for (int k = 1; k <= N; ++k) {
			int tmp = cur;
			last = cur;
			cur = tmp;
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (T[i][k-1][last] == Integer.MAX_VALUE || 
						T[k-1][j][last] == Integer.MAX_VALUE) {
						T[i][j][cur] = T[i][j][last];
					} else {
						T[i][j][cur] = Math.min(T[i][j][last], 
									  T[i][k-1][last] + T[k-1][j][last]);
					}
				}
			}
		}
		
		// determine if there is any negative cycle
		for (int i = 0; i < N; ++i) {
				if (T[i][i][cur] < 0) {
					System.out.println("NULL");
					return;
				}
		}
		
		// find the shortest shortest path
		int ssp = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (ssp > T[i][j][cur]) {
					ssp = T[i][j][cur];
				}
			}
		}
		System.out.println(ssp);
	}
	
	static class Vertex {
		public int id;
		public Map<Vertex, Integer> outs;
		
		public Vertex() {
			id = -1;
			outs = null;
		}
		
		public Vertex(int id) {
			this.id = id;
			outs = new HashMap<Vertex, Integer>();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
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
			return "Vertex [id=" + id + "]";
		}
		
	}
}

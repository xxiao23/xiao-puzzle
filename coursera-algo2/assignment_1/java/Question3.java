import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * In this programming problem you'll code up Prim's minimum spanning tree algorithm. Download the text file here. This file describes an undirected graph with integer edge costs. It has the format
[number_of_nodes] [number_of_edges]
[one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]
[one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
...
For example, the third line of the file is "2 3 -8874", indicating that there is an edge connecting vertex #2 and vertex #3 that has cost -8874. You should NOT assume that edge costs are positive, nor should you assume that they are distinct.

Your task is to run Prim's minimum spanning tree algorithm on this graph. You should report the overall cost of a minimum spanning tree --- an integer, which may or may not be negative --- in the box below.

IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of Prim's algorithm should work fine. OPTIONAL: For those of you seeking an additional challenge, try implementing a heap-based version. The simpler approach, which should already give you a healthy speed-up, is to maintain relevant edges in a heap (with keys = edge costs). The superior approach stores the unprocessed vertices in the heap, as described in lecture. Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.

Answer for Question 
-33612829

 * @author xiang
 *
 */
public class Question3 {
	public static long prim(String input) throws Exception {
		long result = 0;
		
		BufferedReader in = new BufferedReader(new FileReader(input));
		
		String line = in.readLine();
		String[] tmp1 = line.split(" ");
		long numOfVertice = Long.valueOf(tmp1[0]);
		Map<Long, Vertex> g = new HashMap<Long, Vertex>();
		line = in.readLine();
		while (line != null) {
			String[] tmp2 = line.split(" ");
			long id1 = Long.valueOf(tmp2[0]);
			long id2 = Long.valueOf(tmp2[1]);
			long edgeWeight = Long.valueOf(tmp2[2]);
			Vertex v1;
			Vertex v2;
			if (g.containsKey(id1)) {
				v1 = g.get(id1);
			} else {
				v1 = new Vertex(id1);
				g.put(id1, v1);
			}
			if (g.containsKey(id2)) {
				v2 = g.get(id2);
			} else {
				v2 = new Vertex(id2);
				g.put(id2, v2);
			}
			v1.edgeWeights.put(v2, edgeWeight);
			v2.edgeWeights.put(v1, edgeWeight);
			line = in.readLine();
		}
		
		// X : vertice already in X
		Set<Vertex> X = new HashSet<Vertex>();
		// T : predecessor graph of vertice in MST
		Map<Vertex, Vertex> T = new HashMap<Vertex, Vertex>();
		for (Vertex v : g.values()) {
			T.put(v, null);
		}
		
		// create a MIN priority queue
		// for all vertex scores
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>(Long.valueOf(numOfVertice).intValue(),
				new Comparator<Vertex>() {

					@Override
					public int compare(Vertex arg0, Vertex arg1) {
						if (arg0.score > arg1.score) {
							return 1;
						} else if (arg0.score < arg1.score) {
							return -1;
						}
						return 0;
					}
			
		});
		
		Iterator<Vertex> it = g.values().iterator();
		while (it.hasNext()) {
			q.add(it.next());
		}
		
		// choose an arbitrary vertex to start
		Vertex s = g.values().toArray(new Vertex[0])[0];
		q.remove(s);
		s.score = 0;
		q.add(s);
		while (X.size() < numOfVertice) {
			// not all vertice in MST
			Vertex v = q.poll();
			X.add(v);
			System.out.println(v.id + " : " + v.score);
			result += v.score;
			// update each of v's neighbors
			for (Vertex n : v.edgeWeights.keySet()) {
				if (X.contains(n)) {
					continue;
				}
				long weight = v.edgeWeights.get(n);
				if (weight < n.score) {
					n.score = weight;
					q.remove(n);
					q.add(n);
					T.put(n, v);
				}
			}
		}
		return result;
	}
}

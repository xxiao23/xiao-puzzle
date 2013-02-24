import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author xiang
 *
 *In this programming problem and the next you'll code up the clustering algorithm from lecture for computing a max-spacing k-clustering. Download the text file here. This file describes a distance function (equivalently, a complete graph with edge costs). It has the following format:
[number_of_nodes]
[edge 1 node 1] [edge 1 node 2] [edge 1 cost]
[edge 2 node 1] [edge 2 node 2] [edge 2 cost]
...
There is one edge (i,j) for each choice of 1²i<j²n, where n is the number of nodes. For example, the third line of the file is "1 3 5250", indicating that the distance between nodes 1 and 3 (equivalently, the cost of the edge (1,3)) is 5250. You can assume that distances are positive, but you should NOT assume that they are distinct.

Your task in this problem is to run the clustering algorithm from lecture on this data set, where the target number k of clusters is set to 4. What is the maximum spacing of a 4-clustering?

ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases. And then post them to the discussion forum!

Answer for Question 1
You entered:
106
 */
public class Question1 {
	public static long maxSpacingKClustering(int numOfClusters, String input)
		throws Exception{
		
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		String[] tmp1 = line.split(" ");
		int numOfVertice = Integer.valueOf(tmp1[0]);
		Map<Long, Vertex> vertice = new HashMap<Long, Vertex>(numOfVertice);
		List<Edge> edges = new ArrayList<Edge>();
		Map<Vertex, HashSet<Vertex>> inverseClusterMap = 
				new HashMap<Vertex, HashSet<Vertex>>();
		line = in.readLine();
		while (line != null) {
			String[] tmp2 = line.split(" ");
			long id1 = Long.valueOf(tmp2[0]);
			long id2 = Long.valueOf(tmp2[1]);
			long edgeWeight = Long.valueOf(tmp2[2]);
			Vertex v1;
			Vertex v2;
			if (vertice.containsKey(id1)) {
				v1 = vertice.get(id1);
			} else {
				v1 = new Vertex(id1);
				vertice.put(id1, v1);
				HashSet<Vertex> s = new HashSet<Vertex>();
				s.add(v1);
				inverseClusterMap.put(v1, s);
			}
			if (vertice.containsKey(id2)) {
				v2 = vertice.get(id2);
			} else {
				v2 = new Vertex(id2);
				vertice.put(id2, v2);
				HashSet<Vertex> s = new HashSet<Vertex>();
				s.add(v2);
				inverseClusterMap.put(v2, s);
			}
			Edge e = new Edge(v1, v2, edgeWeight);
			edges.add(e);
			line = in.readLine();
		}
		
		// sort edges by weights in the ascending order
		Collections.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge arg0, Edge arg1) {
				if (arg0.weight > arg1.weight) {
					return 1;
				} else if (arg0.weight < arg1.weight) {
					return -1;
				} else {
					return 0;
				}
			}
			
		});
		
		int numOfCurClusters = numOfVertice;
		int i = 0;
		// keep merging clusters by Kruskal's MST algo
		while (numOfCurClusters > numOfClusters) {
			Edge e = edges.get(i);
			++i;
			Vertex c1 = e.a.leader;
			Vertex c2 = e.b.leader;
			if (c1.equals(c2)) {
				// a, b in the same cluster already
				// move on
				continue;
			}
			// merge a's cluster with b's
			HashSet<Vertex> s1 = inverseClusterMap.get(c1);
			HashSet<Vertex> s2 = inverseClusterMap.get(c2);
			if (s1.size() > s2.size()) {
				// merge s2 into s1
				for (Vertex v : s2) {
					v.leader = c1;
				}
				s1.addAll(s2);
			} else {
				// merge s1 into s2
				for (Vertex v : s1) {
					v.leader = c2;
				}
				s2.addAll(s1);
			}
			--numOfCurClusters;
		}
		
		while (i < edges.size()) {
			Edge e = edges.get(i);
			Vertex a = e.a;
			Vertex b = e.b;
			if (!a.leader.equals(b.leader)) {
				break;
			}
			++i;
		}
		
		return edges.get(i).weight;
	}
}

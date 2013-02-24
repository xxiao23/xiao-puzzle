import java.io.BufferedReader;
import java.io.FileReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author xiang
 *
 *In this question your task is again to run the clustering algorithm from lecture, but on a MUCH bigger graph. So big, in fact, that the distances (i.e., edge costs) are only defined implicitly, rather than being provided as an explicit list.
The data set is here. The format is:
[# of nodes] [# of bits for each node's label]
[first bit of node 1] ... [last bit of node 1]
[first bit of node 2] ... [last bit of node 2]
...
For example, the third line of the file "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1" denotes the 24 bits associated with node #2.

The distance between two nodes u and v in this problem is defined as the Hamming distance--- the number of differing bits --- between the two nodes' labels. For example, the Hamming distance between the 24-bit label of node #2 above and the label "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1" is 3 (since they differ in the 3rd, 7th, and 21st bits).

The question is: what is the largest value of k such that there is a k-clustering with spacing at least 3? That is, how many clusters are needed to ensure that no pair of nodes with all but 2 bits in common get split into different clusters?

NOTE: The graph implicitly defined by the data file is so big that you probably can't write it out explicitly, let alone sort the edges by cost. So you will have to be a little creative to complete this part of the question. For example, is there some way you can identify the smallest distances without explicitly looking at every pair of nodes?

Answer for Question 2
You entered:
16508
 */

public class Question2 {
	public static int hammingDistanceMaxK(String input) throws Exception {
				
		BufferedReader in = new BufferedReader(new FileReader(input));
		String line = in.readLine();
		String tmp[] = line.split(" ");
		int numOfVertice = Integer.valueOf(tmp[0]);
		int numOfBits = Integer.valueOf(tmp[1]);
		Set<BitSet> vertice = new HashSet<BitSet>(numOfVertice);
		// cluster map
		// for looking up which cluster a given vertex belongs to
		Map<BitSet, BitSet> clusterMap = new HashMap<BitSet, BitSet>();
		// inverse cluster map
		// for looking up what vertice are in a given cluster
		Map<BitSet, HashSet<BitSet>> iClusterMap = 
				new HashMap<BitSet, HashSet<BitSet>>();
		line = in.readLine();
		int count = 1;
		while (line != null) {
			String tmp2[] = line.split(" ");
			BitSet vertex = new BitSet(numOfBits);
			for (int i = 0; i < numOfBits; ++i) {
				if ("1".equalsIgnoreCase(tmp2[i])) {
					vertex.set(i, true);
				} else {
					vertex.set(i, false);
				}
			}
			vertice.add(vertex);
			clusterMap.put(vertex, vertex);
			HashSet<BitSet> s = new HashSet<BitSet>();
			s.add(vertex);
			iClusterMap.put(vertex, s);
			// read next line
			line = in.readLine();
			count++;
		}
		System.out.println("Read # lines : " + count);

		// validation
		Set<BitSet> clusters = new HashSet<BitSet>();
		for (BitSet v : clusterMap.keySet()) {
			clusters.add(clusterMap.get(v));
		}
		System.out.println("Initial count # clusters : " + clusters.size());
		System.out.println("Distinct nodes : " + vertice.size());
		
		BitSet[] verticeArr = vertice.toArray(new BitSet[1]);
		int numOfDistinctVertice = verticeArr.length;
		int numOfClusters = numOfDistinctVertice;
		// for each node
		// merge any node with Hamming distance <= 2
		for (int i = 0; i < numOfDistinctVertice; ++i) {
			BitSet v = verticeArr[i];
			// deal with distance = 1
			for (int j = 0; j < numOfBits; ++j) {
				BitSet n = new BitSet(numOfBits);
				n.clear();
				n.or(v);
				n.flip(j); // flip jth bit
				if (clusterMap.containsKey(n)) {
					// found distance 1
					BitSet c1 = clusterMap.get(v);
					BitSet c2 = clusterMap.get(n);
					if (c1.equals(c2)) {
						continue; // already in the same cluster
					}
					// merge two clusters
					HashSet<BitSet> s1 = iClusterMap.get(c1);
					HashSet<BitSet> s2 = iClusterMap.get(c2);
					if (s1.size() < s2.size()) {
						// merge s1 into s2
						for (BitSet x : s1) {
							clusterMap.put(x, c2);
						}
						s2.addAll(s1);
					} else {
						// merge s2 into s1
						for (BitSet x : s2) {
							clusterMap.put(x, c1);
						}
						s1.addAll(s2);
					}
					numOfClusters--;
				}
			}
			
			// deal with distance = 2
			for (int j = 0; j < numOfBits; j++) {
				for (int jj = j+1; jj < numOfBits; jj++) {
					BitSet n = new BitSet(numOfBits);
					n.clear();
					n.or(v);
					n.flip(j); // flip jth bit
					n.flip(jj); // flip jjth bit
					if (clusterMap.containsKey(n)) {
						// found distance 1
						BitSet c1 = clusterMap.get(v);
						BitSet c2 = clusterMap.get(n);
						if (c1.equals(c2)) {
							continue; // already in the same cluster
						}
						// merge two clusters
						HashSet<BitSet> s1 = iClusterMap.get(c1);
						HashSet<BitSet> s2 = iClusterMap.get(c2);
						if (s1.size() < s2.size()) {
							// merge s1 into s2
							for (BitSet x : s1) {
								clusterMap.put(x, c2);
							}
							s2.addAll(s1);
						} else {
							// merge s2 into s1
							for (BitSet x : s2) {
								clusterMap.put(x, c1);
							}
							s1.addAll(s2);
						}
						numOfClusters--;
					}
				}
			}
		}
		
		// validation
		clusters = new HashSet<BitSet>();
		for (BitSet v : clusterMap.keySet()) {
			clusters.add(clusterMap.get(v));
		}
		System.out.println("Count # clusters : " + clusters.size());
		return numOfClusters;
	}
}

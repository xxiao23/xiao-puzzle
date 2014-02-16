/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    Map<UndirectedGraphNode, UndirectedGraphNode> clonedNodes = null;
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        // Note: The Solution object is instantiated only once and is reused by each test case.
        clonedNodes = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        //dfs(node);
        bfs(node);
        return clonedNodes.get(node);
    }

    private void bfs(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        clonedNodes.put(node, new UndirectedGraphNode(node.label));
        q.add(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode n = q.remove();
            UndirectedGraphNode nn = clonedNodes.get(n);
            
            for (UndirectedGraphNode v : n.neighbors) {
                if (!clonedNodes.containsKey(v)) {
                    UndirectedGraphNode nv = new UndirectedGraphNode(v.label);
                    clonedNodes.put(v, nv);
                    q.add(v);
                }
                nn.neighbors.add(clonedNodes.get(v));
            }
        }
    }
}

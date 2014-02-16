/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> graphMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        graphMap.put(node, newNode);
        dfs(node, graphMap);
        return newNode;
    }
    
    private void dfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> graphMap) {
        UndirectedGraphNode newNode = graphMap.get(node);
        for (UndirectedGraphNode v : node.neighbors) {
            boolean doDfs = false;
            if (!graphMap.containsKey(v)) {
                // create the neighbor
                UndirectedGraphNode newV = new UndirectedGraphNode(v.label);
                graphMap.put(v, newV);
                doDfs = true;
            }
            // create the bi-directional edges
            UndirectedGraphNode newV = graphMap.get(v);
            newNode.neighbors.add(newV);
            if (doDfs) dfs(v, graphMap);
        }
        
    }
}

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

    public static int minDistance(Graph graph, String src, String dest) {
        if (graph == null || !graph.containsElement(src) || !graph.containsElement(dest)) {
            return -1;
        }

        Node srcNode = graph.getNode(src);
        Map<Node, Integer> minDistanceMap = new HashMap<>(Collections.singletonMap(srcNode, 0));
        Queue<Node> toBeDiscovered = new LinkedList<>(Collections.singletonList(srcNode));
        while (!toBeDiscovered.isEmpty()) {
            Node currentNode = toBeDiscovered.remove();
            if (currentNode.equals(graph.getNode(dest))) {
                return minDistanceMap.get(currentNode);
            }
            for (Node neighbor : graph.getNodeNeighbors(currentNode)) {
                if (!minDistanceMap.containsKey(neighbor)) {
                    toBeDiscovered.add(neighbor);
                    minDistanceMap.put(neighbor, minDistanceMap.get(currentNode) + 1);
                }
            }
        }

        return -1;
    }


    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
        if (graph == null || !graph.containsElement(src) || distance <= 0) {
            return null;
        }

        Node srcNode = graph.getNode(src);
        Map<Node, Integer> distancesToSrcNode = new HashMap<>(Collections.singletonMap(srcNode, 0));
        Queue<Node> toBeDiscovered = new LinkedList<>(Collections.singletonList(srcNode));
        Set<String> nodesWithinDistance = new HashSet<>();
        while (!toBeDiscovered.isEmpty()) {
            Node currentNode = toBeDiscovered.remove();
            Integer distanceToSrcFromCurrentNode = distancesToSrcNode.get(currentNode);
            for (Node neighbor : graph.getNodeNeighbors(currentNode)) {
                if (!distancesToSrcNode.containsKey(neighbor)) {
                    toBeDiscovered.add(neighbor);
                    if (distanceToSrcFromCurrentNode + 1 <= distance && !neighbor.equals(srcNode)) {
                        nodesWithinDistance.add(neighbor.toString());
                    }
                    distancesToSrcNode.put(neighbor, distanceToSrcFromCurrentNode + 1);
                }
            }
        }

        return nodesWithinDistance;
    }


    public static boolean isHamiltonianPath(Graph g, List<String> values) {
        if (g == null || values == null || values.isEmpty() || !values.get(0).equalsIgnoreCase(values.get(values.size() - 1))) {
            return false;
        }

        Set<Node> nodesInHamiltonianPath = new HashSet<>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) == null || values.get(i).isEmpty()) {
                return false;
            }

            Node currentNode = g.getNode(values.get(i));
            if (currentNode == null) {
                return false;
            }
            if (i > 0 && !g.getNodeNeighbors(g.getNode(values.get(i - 1))).contains(currentNode)) {
                return false;
            }
            if (nodesInHamiltonianPath.add(currentNode)) {
                continue;
            }
            if (i < values.size() - 1) {
            	return false;
			}
        }
        return g.getAllNodes().equals(nodesInHamiltonianPath);
    }
}
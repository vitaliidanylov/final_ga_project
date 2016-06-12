package dijkstra2.test;

import dijkstra2.engine.DijkstraAlgorithm;
import dijkstra2.model.Edge;
import dijkstra2.model.Graph;
import dijkstra2.model.Vertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

        addLane("Edge_0", 0, 1, 20);
        addLane("Edge_1", 0, 2, 12);
        addLane("Edge_2", 0, 3, 9);
        addLane("Edge_2", 1, 2, 11);
        addLane("Edge_2", 1, 4, 13);
        addLane("Edge_3", 2, 3, 3);
        addLane("Edge_4", 2, 4, 4);
        addLane("Edge_5", 3, 5, 10);
        addLane("Edge_6", 4, 8, 6);
        addLane("Edge_7", 5, 6, 7);
        addLane("Edge_8", 5, 7, 8);
        addLane("Edge_9", 6, 7, 5);
        addLane("Edge_10", 6, 8, 16);
        addLane("Edge_11", 6, 9, 18);
        addLane("Edge_12", 7, 9, 21);
        addLane("Edge_13", 8, 9, 2);

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex);
        }

    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
    }
}

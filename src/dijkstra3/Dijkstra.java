package dijkstra3;

/**
 * Created by vit on 6/12/2016.
 */

import java.text.DecimalFormat;

public class Dijkstra {
    private static final Graph.Edge[] GRAPH = {
            new Graph.Edge("a", "1", 20),
            new Graph.Edge("a", "2", 12),
            new Graph.Edge("a", "3", 9),
            new Graph.Edge("1", "2", 11),
            new Graph.Edge("1", "4", 13),
            new Graph.Edge("2", "3", 3),
            new Graph.Edge("2", "4", 4),
            new Graph.Edge("3", "5", 10),
            new Graph.Edge("4", "8", 6),
            new Graph.Edge("5", "6", 7),
            new Graph.Edge("5", "7", 8),
            new Graph.Edge("6", "7", 5),
            new Graph.Edge("6", "8", 16),
            new Graph.Edge("6", "e", 18),
            new Graph.Edge("7", "e", 21),
            new Graph.Edge("8", "e", 2),
    };
    private static final String START = "a";
    private static final String END = "e";

    public static void main(String[] args) {
        final long startTime = System.nanoTime();
        Graph g = new Graph(GRAPH);
        g.dijkstra(START);
        g.printPath(END);
        final double estimatedTime  = (System.nanoTime() - startTime)/1000000000.0;
//        double seconds = (double)estimatedTime / 1000000000.0;
//        double seconds = (double)estimatedTime;
//        System.out.println("Time of calculations is: " + seconds);

        DecimalFormat df = new DecimalFormat("#.#########");
        df.setMaximumFractionDigits(8);
        System.out.println(df.format(estimatedTime));
        //g.printAllPaths();
    }
}

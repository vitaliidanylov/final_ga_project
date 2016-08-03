package dijkstra;

/**
 * Created by vit on 5/24/2016.
 */
// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Stack;

class ShortestPath {
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    static final int V = 500;

    int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    void printSolution(int dist[], int n) {
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
        System.out.println("Shortest path: " + dist[V - 1]);
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src) {
        int start = src,
                end = V - 1;
        int dist[] = new int[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i
        int prev[] = new int[V];
        Arrays.fill(prev, -1);
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
        }

        Stack<Integer> stack = new Stack<Integer>();
        for (int v = end; v != -1; v = prev[v]) {
            stack.push(v);
        }
        int[] sp = new int[stack.size()];
        for (int i = 0; i < sp.length; i++)
            sp[i] = stack.pop() + 1;
        System.out.printf("Кратчайшее расстояние между %d и %d = %d%n", start + 1, end + 1, dist[end]);
        System.out.println("Кратчайший путь: " + Arrays.toString(sp));
        // print the constructed distance array
//        printSolution(dist, V);
    }

    // Driver method
    public static void main(String[] args) {
        String path = "src/datasets_dijkstra/matrix500x500d.txt";
        /* Let us create the example graph discussed above */
        int graph[][] = MatrixFromFile.readMatrixFromFile(path);
//        MatrixFromFile.printMatrix(graph);

        for (int i = 0; i < 31; i++) {
            //start time
            final double startTime = System.nanoTime();
            ShortestPath t = new ShortestPath();
            t.dijkstra(graph, 0);
            //end time
            final double estimatedTime = (System.nanoTime() - startTime) / 1000000000.0;

            DecimalFormat df = new DecimalFormat("#.#########");
            df.setMaximumFractionDigits(8);
            System.out.println(df.format(estimatedTime));
        }

    }
}
//This code is contributed by Aakash Hasija
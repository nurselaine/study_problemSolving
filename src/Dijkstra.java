import java.util.Arrays;

public class Dijkstra {

    public static void main(String[] args){
        int graph[][] = new int[][] { { 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0, 3, 0 }, { 1, 2, 0, 1, 3, 0, 0 },
                { 2, 0, 1, 0, 0, 0, 1 }, { 0, 0, 3, 0, 0, 2, 0 }, { 0, 3, 0, 0, 2, 0, 1 }, { 0, 0, 0, 1, 0, 1, 0 } };
        dijkstra(graph, 0);
    }

    /**
     * Use dijkstra'a algorithm to find the shortest path
     * from src to dest node
     *
     * PLAN
     * declare a visited list and a minHeap
     * - min heap will track the shortest path node from each node
     *
     * 
     * */
    public static int shortestPath(int[][] graph, int src, int dest){

    }


    /**
     * Dijkstra's algorithm find the shortest path from a src
     * node to ALL other vertices in the graph
     *
     * Using a visited array and a distance array, we can track
     * */
    public static void dijkstra(int[][] graph, int src){
        int count = graph.length;
        boolean[] visitedVertex = new boolean[count];
        Arrays.fill(visitedVertex, false);
        int[] distance = new int[count];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // update distance of src to 0
        distance[src] = 0;

        // iterate over all vertices
        for(int i = 0; i < count; i++){

            // find the min distance between current vertex neighbors
            int u = findMinDistance (distance, visitedVertex);
            visitedVertex[u] = true;

            // update neighboring vertex distance values
            for(int v = 0; v < count; v++){
                if(!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])){
                    distance[v] = distance[u] + graph[u][v];
                }
            }
        }

        for (int i = 0; i < distance.length; i++) {
            System.out.println(String.format("Distance from %s to %s is %s", src, i, distance[i]));
        }
    }

    private static int findMinDistance(int[] distance, boolean[] visitedVertex){
        int minDistance = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for(int i = 0; i < distance.length; i++){
            if(!visitedVertex[i] && distance[i] < minDistance){
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }
}

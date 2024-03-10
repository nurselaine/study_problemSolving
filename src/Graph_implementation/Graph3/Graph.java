package Graph_implementation.Graph3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Graph<T> {

    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Vertex>> adjacencyList;
    private int[][] adjacencyMatrix;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean isDirected, boolean isWeighted){
        // initialize empty vertex list
        // initialize empty adjacency set
        // initialize empty matrix list
        // if isWeighted initialize empty edges list
    }

    // add vertex
    // create new vertex
    // add vertex to vertices list and update adjacency list

    // add adjacency
    // takes in a src and dest vertex
    // add end vertex to start bucket in adjacency list
    // if undirected graph -> add start vertex to end bucket in adjacency list
    // if weighted create + add edge to edges list

    // dfs
    // take arbitrary starting node
    // create a visited list and unvisited stack
    // add starting node to unvisited stack
    // call dfs helper
    // create string from visited list and return
    //
    // dfs helper
    // vertex has been visited or vertex does not have neighbors then return
    // add vertex to visited list
    // get vertex neighbors
    // iterate over neighbors
    // if not found in visited list then add to unvisited stack
    // pop off top vertex from unvisited stack and call dfs helper with popped vertex

    // bfs
    // take arbitrary starting node
    // create a queue and add starting node
    // create a visited list
    // while queue is not empty
        // remove vertex from queue and add to visited list
        // iterate over vertex's neighbors
        // if neighbor is not in visited list and not in queue
            // add neighbor to queue
    // create string from visited list and return

    // topological sort (Khan's algorithm)
    // take arbitrary starting node
    // create a list of visited nodes
    // create a list of inbound degree values for each node in graph
    // create a queue and add all vertices with inbound degree of 0
    // while queue is not empty
        // remove vertex from queue
        // add vertex to visited list
        // iterate over vertex neighbors
            // update vertex neighbors degree -1
            // if neighbor degree is 0 then add to queue
    // create string from visited list and return

    // get data

    // get vertex

    // get neighbors

    // convert to matrix

    class Vertex<T> {

        private T data;
        private List<T> neighbors;
        public Vertex(T data, List<T> neighbors){
            this.data = data;
            this.neighbors = neighbors;
        }

        public Vertex(T data){
            this.data = data;
            this.neighbors = new ArrayList<>();
        }
    }
}
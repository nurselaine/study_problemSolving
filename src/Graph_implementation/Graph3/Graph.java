package Graph_implementation.Graph3;

import java.security.InvalidParameterException;
import java.util.*;

public class Graph<T> {

    private List<Vertex> vertices;
    private List<Edge> edges;
    private Map<Vertex, List<Vertex>> adjacencyList;
    private int[][] adjacencyMatrix;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean isDirected, boolean isWeighted){
        this.vertices = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
        this.isWeighted = isWeighted;
        this.isDirected = isDirected;
        // initialize empty vertex list
        // initialize empty adjacency set
        // initialize empty matrix list
        // if isWeighted initialize empty edges list
    }

    // add vertex
    // create new vertex
    // add vertex to vertices list and update adjacency list
    public Graph add(T data){
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        this.adjacencyList.put(newVertex, new ArrayList<>());
        return this;
    }

    // add adjacency
    // takes in a src and dest vertex
    // add end vertex to start bucket in adjacency list
    // if undirected graph -> add start vertex to end bucket in adjacency list
    public Graph addEdge(T src, T dest){
        Vertex start = this.getVertex(src);
        Vertex end = this.getVertex(dest);
        if(start == null || end == null) throw new InvalidParameterException("Src or Dest vertex does not exists");

        this.adjacencyList.get(start).add(end);
        if(!this.isDirected){
            this.adjacencyList.get(end).add(start);
        }
        return this;
    }

    // add adjacency with weight
    // takes in a src and dest vertex and weight value
    // create new edge and add to edge list
    // add end vertex to start bucket in adjacency list
    // if undirected graph -> add start vertex to end bucket in adjacency list
    // if weighted create + add edge to edges list
    public Graph addEdge(T src, T dest, int weight){
        Vertex start = this.getVertex(src);
        Vertex end = this.getVertex(dest);
        this.edges.add(new Edge(start, end, weight));
        if(start == null || end == null) throw new InvalidParameterException("Src or Dest vertex does not exists");

        this.adjacencyList.get(src).add(end);
        if(!this.isDirected){
            this.adjacencyList.get(end).add(start);
        }
        if(this.isWeighted){

        }
        return this;
    }

    // dfs
    // take arbitrary starting node
    // create a visited list
    // iterate over all nodes in graph
    // call dfs helper
    //
    // dfs helper
    // add curr vertex to visited list
    // get vertex neighbors + iterate over neighbors
    // if not found in visited list then call dfsHelper with neighbor node
    public String DFS(){
        Set<Vertex> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder("DFS:");
        for(Vertex v : this.vertices){
            if(!visited.contains(v)){
                DFS(v, visited, sb);
            }
        }
        return sb.toString();
    }

    private void DFS(Vertex root, Set<Vertex> visited, StringBuilder sb){
        visited.add(root);
        sb.append(" " + root.data());

        for(Vertex neighbor : this.getNeighbors(root)){
            if(!visited.contains(neighbor)){
                DFS(neighbor, visited, sb);
            }
        }
    }

    // BFS
    // Use a visited list and a queue
    // iterate over all vertices in the list
    // if the vertex has not been visited then add curr vertex into queue
    // while the queue is not empty then pop off from queue
    // add vertex to visited
    // iterate over curr neighbors
    // if neighbor is not in visited or in queue then add to queue
    public String BFS(){
        Queue<Vertex> queue = new LinkedList<>();
        List<Vertex> visited = new ArrayList<>();
        StringBuilder sb = new StringBuilder("BFS:");
        for(Vertex v : this.vertices){
            if(!visited.contains(v) && !queue.contains(v)){
                queue.add(v);

                while(!queue.isEmpty()){
                    Vertex curr = queue.remove();
                    visited.add(curr);
                    for(Vertex neighbor : this.getNeighbors(curr)){
                        if(!visited.contains(neighbor) && !queue.contains(neighbor)){
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }

        for(Vertex v : visited){
            sb.append(" " + v.data());
        }

        return sb.toString();
    }

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
    public String topologicalSort(T root){
        Vertex start = this.getVertex(root);
        if(start == null) throw new InvalidParameterException("Vertex does not exist in graph");
        StringBuilder sb = new StringBuilder("Topological Sort: ");

        List<Vertex> visited = new ArrayList<>();
        int[] degree = new int[this.vertices.size()];
        for(Vertex v : this.adjacencyList.keySet()){
            for(Vertex neighbor : this.getNeighbors(v)){
                degree[this.vertices.indexOf(neighbor)]++;
            }
        }

        Queue<Vertex> queue = new LinkedList<>();
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == 0){
                queue.add(this.vertices.get(i));
            }
        }

        while(!queue.isEmpty()){
            Vertex curr = queue.remove();
            visited.add(curr);
            for(Vertex v : this.getNeighbors(curr)){
                int index = this.vertices.indexOf(v);
                if(--degree[index] == 0){
                    queue.add(v);
                }
            }
        }

        for(Vertex v : visited){
            sb.append(" " + v.data());
        }
        return sb.toString();
    }

    // Topological sort with DFS
    // Use a visited set and a stack to hold visited vertices in reverse order
    // for each vertex in the graph
    // if it is not visited then call dfs
    // dfs
    // add node to visited list
    // iterate over node's neighbors if they are not visited
    // if node no longer has any unvisited neighbors then add to stack
    // stack will add in node's with the highest in-degree and hold the values with the least or 0 indegree at the top
    // once done traversing graph, remove each node from the stack and add to a string to be returned
    public String topologicalRecursiveSort(){
        StringBuilder sb = new StringBuilder("Topological Recursive Sort:");

        Set<Vertex> visited = new HashSet<>();
        Stack<Vertex> result = new Stack<>();
        for(Vertex v : this.vertices){
            if(!visited.contains(v)){
                topologicalRecursiveSort(v, visited, result);
            }
        }

        while(!result.isEmpty()){
            sb.append(" " + result.pop().data());
        }
        return sb.toString();
    }

    private void topologicalRecursiveSort(Vertex root, Set<Vertex> visited, Stack<Vertex> result){
        visited.add(root);

        for(Vertex neighbor : this.getNeighbors(root)){
            if(!visited.contains(neighbor)){
                topologicalRecursiveSort(neighbor, visited, result);
            }
        }
        result.push(root);
    }

    // with : add new vertex & adjacency
    // should take in src vertex and list of adjacency (adjacency may need to be instantiated)
    // if src does not exist then create new vertex
    // iterate over list of adjacent vertices
    // if neighbor does not exist then create new vertex
    // add neighbor to adjacency list in src vertex bucket
    // return this
    public Graph with(T src, T[] adjacency){
        Vertex start = this.getVertex(src);
        if(start == null){
            start = new Vertex(src);
            this.adjacencyList.put(start, new ArrayList<>());
            this.vertices.add(start);
        }

        for(T v : adjacency){
            Vertex vertex = this.getVertex(v);
            if(vertex == null){
                vertex = new Vertex(v);
                this.adjacencyList.put(vertex, new ArrayList<>());
                this.vertices.add(vertex);
            }
            this.adjacencyList.get(start).add(vertex);
            if(!this.isDirected){
                this.adjacencyList.get(vertex).add(start);
            }
        }
        return this;
    }

    // get vertex
    // iterate over vertices list
    // check if vertex data matches given data
    public Vertex getVertex(T data){
        for(Vertex v : this.vertices){
            if(v.data().equals(data)){
                return v;
            }
        }
        return null;
    }

    // get neighbors
    // if adjacency list does not contain vertex return empty list
    // get list from adjacency list and return
    public List<Vertex> getNeighbors(Vertex v){
        if(!this.adjacencyList.containsKey(v)) throw new InvalidParameterException("Vertex not found in graph.");

        return this.adjacencyList.get(v);
    }

    // Detect a cycle

    // convert to matrix

    // toString
    @Override
    public String toString(){
        if(this.adjacencyList.isEmpty()) return "Graph is empty";
        StringBuilder sb = new StringBuilder();
        for(Vertex v : this.adjacencyList.keySet()){
            sb.append(v.data() + " :");
            for(Vertex neighbor : this.getNeighbors(v)){
                sb.append(" " + neighbor.data());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String getVertices(List<Vertex> vertices){
        StringBuilder sb = new StringBuilder();
        for(Vertex v : vertices){
            sb.append(" " + v.data());
        }
        return sb.toString();
    }

}
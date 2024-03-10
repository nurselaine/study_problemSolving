

package Graph_implementation.Graph2;

import java.util.*;

public class Graph {

    private Map<Vertex, List<Vertex>> vertices;
    private List<Edge> edges;
    private boolean isWeight;
    private boolean isDirected;

    public Graph(boolean isWeight, boolean isDirected){
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
        this.isWeight = isWeight;
        this.isDirected = isDirected;
    }

    // add vertex
    public Graph addVertex(String data){
        if(vertices.containsKey(data)) return this;

        Vertex newVertex = new Vertex(data);
        vertices.put(newVertex, new ArrayList<>());
        return this;
    }

    // remove vertex
    public Graph removeVertex(String data){
        vertices.remove(this.getVertex(data));
        return this;
    }

    // add edge
    public Graph addEdge(String v1, String v2, int weight){
        Vertex start = this.getVertex(v1);
        Vertex end = this.getVertex(v2);
        Edge newEdge = new Edge(start, end, weight);

        if(this.isWeight) this.edges.add(newEdge);

        this.vertices.get(start).add(end);

        if(!this.isDirected){
            vertices.get(end).add(start);
        }

        return this;
    }

    // add edge for un-weighted graph
    public Graph addEdge(String v1, String v2){
        Vertex start = this.getVertex(v1);
        Vertex end = this.getVertex(v2);

        this.vertices.get(start).add(end);

        if(!this.isDirected){
            vertices.get(end).add(start);
        }

        return this;
    }

    // remove edge
    public Graph removeEdge(String v1, String v2){
        Vertex start = this.getVertex(v1);
        Vertex end = this.getVertex(v2);

        if(this.isWeight){
            // remove edge from edges
            Edge oldEdge = getEdge(start, end);
            this.edges.remove(oldEdge);
        }
        this.vertices.get(start).remove(end);
        if(!this.isDirected){
            this.vertices.get(end).remove(start);
        }
        return this;
    }

    // getters

    // get vertex by data
    public Vertex getVertex(String data){
        for(Vertex v : vertices.keySet()){
            if(v.getData().equals(data)){
                return v;
            }
        }
        return null;
    }

    public Edge getEdge(Vertex start, Vertex end){
        for(Edge e : edges){
            if(e.getV1().equals(start) && e.getV2().equals(end)){
                return e;
            }
        }
        return null;
    }

    // print graph
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<Vertex, List<Vertex>> entry : this.vertices.entrySet()){
            sb.append(entry.getKey().getData() + " ->");
            for(Vertex v : entry.getValue()){
                sb.append(" " + v.getData());
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public String dfs(){
        Set<Vertex> visited = new HashSet<>();
        // a g d e
        Stack<Vertex> unvisited = new Stack<>();
        // b f
        unvisited.add(this.getVertex("a"));
        StringBuilder sb = new StringBuilder();
        sb.append("DFS:");

        while(!unvisited.isEmpty()){
            Vertex v = unvisited.pop();
            sb.append(" " + v.getData());
            visited.add(v);

            List<Vertex> neighbors = this.vertices.get(v);
            for(Vertex n : neighbors){
                if(!visited.contains(n) && !unvisited.contains(n)){
                    unvisited.add(n);
                }
            }
        }
        return sb.toString();
    }

    public String bfs(){
        // visit all children first
        // use a queue to hold add children in FIFO order
        Queue<Vertex> queue = new LinkedList<>();
        Set<Vertex> visited = new HashSet<>();
        // add the first node into queue
        queue.add(this.getVertex("a"));
        StringBuilder sb = new StringBuilder();
        sb.append("BFS:");
        // loop while queue is empty
        while(!queue.isEmpty()){
            // remove vertex from queue
            Vertex popped = queue.remove();
            visited.add(popped);
            sb.append(" " + popped.getData());

            // add all element neighbors to queue
            List<Vertex> neighbors = this.vertices.get(popped);
            for(Vertex n : neighbors){
                if(!visited.contains(n) && !queue.contains(n)){
                    queue.add(n);
                }
            }
        }

        return sb.toString();
    }

    public String topologicalSort(){
        List<Vertex> result = new ArrayList<>();
        Map<Vertex, Integer> indegreeMap = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();

        // add all vertices to indegree map
        for(Vertex v : this.vertices.keySet()){
            indegreeMap.put(v, 0);
        }

        // update inbound degree for each indegreeMap values
        for(Vertex v : indegreeMap.keySet()){
            for(Vertex neighbor : this.vertices.get(v)){
                indegreeMap.put(neighbor, indegreeMap.getOrDefault(neighbor, 0) + 1);
            }
        }

        for(Map.Entry<Vertex, Integer> entry : indegreeMap.entrySet()){
            if(entry.getValue() == 0){
                queue.add(entry.getKey());
            }
        }

        while(!queue.isEmpty()){
            Vertex curr = queue.remove();
            result.add(curr);

            List<Vertex> currNeighbors = this.vertices.get(curr); 
            for(Vertex v : currNeighbors){
                indegreeMap.put(v, indegreeMap.get(v) - 1);
                if(indegreeMap.get(v) == 0){
                    queue.add(v);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Topological Sort: ");
        for(Vertex v : result){
            sb.append(v.getData() + " ");
        }
        return sb.toString();
    }

}

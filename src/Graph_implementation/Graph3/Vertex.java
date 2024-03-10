package Graph_implementation.Graph3;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String data;
    private List<Edge> edges;

    public Vertex(String value){
        this.data = value;
        this.edges = new ArrayList<>();
    }

    public Vertex(String value, List<Edge> edges){
        this.data = value;
        this.edges = edges;
    }

    // add edge method adds a edge to this edges list
    public void addEdge(Vertex v2, int weight){
        this.edges.add(new Edge(this, v2, weight));
    }

    // remove edge method removes vertex from edges list
    public void removeEdges(Vertex v2){
        if(!this.edges.contains(v2)){
            return;
        }
        this.edges.remove(v2);
    }

    // getters
    public String getData(){
        return this.data;
    }

    public List<Edge> getEdges(){
        return this.edges;
    }

}

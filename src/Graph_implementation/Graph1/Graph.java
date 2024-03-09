

package Graph_implementation.Graph1;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Vertex> vertices;
    private List<Edge> edges;
    private boolean isWeight;
    private boolean isDirected;

    public Graph(boolean isWeight, boolean isDirected){
        this.vertices = new ArrayList<>();
        this.isWeight = isWeight;
        this.isDirected = isDirected;
    }

    // add vertex
    public Graph addVertex(String data){
        // do not add vertex if it exists already
        if(getVertex(data) != null){
            return this;
        }
        Vertex newVertex = new Vertex(data);
        vertices.add(newVertex);
        return this;
    }

    // remove vertex
    public void removeVertex(Vertex vertex){
        this.vertices.remove(vertex);
    }

    // add edge for weighted class
    public Graph addEdge(Vertex v1, Vertex v2, int weight){
        if(!this.isWeight){
            weight = -1;
        }
        v1.addEdge(v2, weight);

        if(!this.isDirected){
            v2.addEdge(v1, weight);
        }
        return this;
    }

    // add edge for unweighted class
    public Graph addEdge(Vertex v1, Vertex v2){

        v1.addEdge(v2, 0);

        if(!this.isDirected){
            v2.addEdge(v1, 0);
        }
        return this;
    }

    // remove edge
    public void removeEdge(Vertex v1, Vertex v2){
        v1.removeEdges(v2);
        if(!this.isDirected){
            v2.removeEdges(v1);
        }
    }

    // getters
    public boolean isWeight(){
        return this.isWeight;
    }

    public boolean isDirected(){
        return this.isDirected;
    }

    // find vertex in graph
    public Vertex getVertex(String data){
        for(Vertex v : this.vertices){
            if(v.getData().equals(data)){
                return v;
            }
        }
        return null;
    }

    // display all vertices
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Vertex v : this.vertices){
            sb.append(v.getData());
        }
        return sb.toString();
    }


}

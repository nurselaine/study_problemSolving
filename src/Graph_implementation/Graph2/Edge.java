package Graph_implementation.Graph2;

public class Edge {

    // starting vertex
    private Vertex v1;
    // end vertex
    private Vertex v2;

    // validates whether edge represents value, will be -1 if graph is not weighted
    private int weight;
    public Edge(Vertex v1, Vertex v2, int weight){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    // getters
    public Vertex getV1(){
        return this.v1;
    }

    public Vertex getV2(){
        return this.v2;
    }

    public int getWeight(){
        return this.weight;
    }
}

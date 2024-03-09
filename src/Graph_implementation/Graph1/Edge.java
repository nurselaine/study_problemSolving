package Graph_implementation.Graph1;

public class Edge {

    // starting vertex
    private Vertex v1;
    // end vertex
    private Vertex v2;

    // validates whether edge represents value, will be -1 if graph is not weighted
    private int weight;
    public Edge(Vertex v1, Vertex v2, int weighted){
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weighted;
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

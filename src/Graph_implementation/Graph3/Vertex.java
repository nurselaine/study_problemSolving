package Graph_implementation.Graph3;

import java.util.List;

class Vertex<T> {

    private T data;
    public Vertex(T data, List<T> neighbors){
        this.data = data;
    }

    public Vertex(T data){
        this.data = data;
    }

    // get data
    public T data(){
        return this.data;
    }
}
import Graph_implementation.Graph3.Vertex;
import Graph_implementation.Graph3.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    public static void main(String[] args){
        Graph g = new Graph(true, false);
        g.with("primer", new String[]{"foundation"})
            .with("foundation", new String[]{"contour", "concealer"})
            .with("concealer", new String[]{"highlighter", "blush"})
            .with("lip balm", new String[]{"lipstick", "lip gloss"})
            .add("powder");

        TopologicalSort(g);

    }

    public static void TopologicalSort(Graph root){

        // Use a visited list to track visited edges
        List<Vertex> visited = new ArrayList<>();
        // use a stack to track all visited vertices
        Stack<String> stack = new Stack<>();
        // iterate over all verticies
        List<Vertex> vertices = root.getVertices();
        for(Vertex v : vertices){
            if(!visited.contains(v)){
                DFS(root, v, visited, stack);
            }
        }

        while(!stack.isEmpty()){
            System.out.println(stack.pop() + " ");
        }
        // for each vertex
        // call helper dfs fn
        // add to visited list
        // get children of vertex
        // call dfs on each child
        // add the most dependent child to the stack

        // once all vertices have been added to the stack, pop it off
        // into and print out the results

    }

    private static void DFS(Graph g, Vertex root, List<Vertex> visited, Stack<String> stack){
        visited.add(root);

        List<Vertex> neighbors = g.getNeighbors(root);
        for(Vertex n : neighbors){
            DFS(g, n, visited, stack);
        }

        stack.add((String) root.data());
    }
}

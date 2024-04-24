import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AI {

    public static void main(String[] args){
        Node root = new Node(1,
                new Node(2,
                        new Node(4,
                                new Node(8), new Node(9)),
                        new Node(5,
                                new Node(10), new Node(11))),
                new Node(3,
                        new Node(6,
                                new Node(12), new Node(13)),
                        new Node(7,
                                new Node(14), new Node(15))));


    }

    public static void BFS(Node root, int goal){
        if(root.val == goal){
            System.out.print("goal reached " + root.val);
            return;
        }
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(root);
        Set<Node> explored = new HashSet<>();
        while(!frontier.isEmpty()){
            Node curr = frontier.remove();
            explored.add(curr);

        }
    }
}

import Node.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static void main(String[] args){
        Node tree = new Node(3, new Node(1, null, new Node(2)), new Node(4));
        List<List<Integer>> result = levelOrderRecursive(tree);
        System.out.println(result);
        result = levelOrdeTraversal(tree);
        System.out.println(result);
    }

    // Implement BFS and add all the nodes in the tree
    // into a list of list where each levels node is separated into
    // its own list
    // PLAN
    // Implement BFS iteratively using a queue and a results list
    // add root into queue and while the queue is not empty
    // declare a num children counter and assign to queue size
    // declare a sublist to hold children
    // remove from queue num children times and add node value into sublist
    // each time check if removed node has children and add into queue
    // add sublist to results list
    public static List<List<Integer>> levelOrdeTraversal(Node root){
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();

        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> sublist = new ArrayList<>();
            int numChildren = queue.size();
            for(int i = 0; i < numChildren; i++){
                Node curr = queue.remove();
                sublist.add(curr.val);
                if(curr.left != null) queue.add(curr.left);
                if(curr.right != null) queue.add(curr.right);
            }
            if(!sublist.isEmpty()) result.add(sublist);
        }
        return result;
    }

    // PLAN
    // create a results list and declare a level coutner
    // use a recursive Helper fn
    // Check if root is null and return if it is
    // check if
    // 1. results list size is <= to level
    //  if it is then add a new list to results list
    //  then add the current node to the newly added list
    // call helper fn to add children from the left and right subtrees
    public static List<List<Integer>> levelOrderRecursive(Node root){
        List<List<Integer>> result = new ArrayList<>();
        helper(root, result, 0);
        return result;
    }

    private static void helper(Node root,List<List<Integer>> result, int level ){
        if(root == null) return;

        if(result.size() <= level){
            result.add(new ArrayList<>());
        }

        result.get(level).add(root.val);
        helper(root.left, result, level + 1);
        helper(root.right, result, level + 1);
    }
}

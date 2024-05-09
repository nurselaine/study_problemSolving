import Node.Node;

import java.util.PriorityQueue;

public class findKthSmallestBST {

    public static void main(String[] args){
        Node tree = new Node(3, new Node(1, null, new Node(2)), new Node(4));
        int result = findKethSmallestBST(tree, 2);
        System.out.println(result);
        result = findKthSmallestBST2(tree, 1);
        System.out.println(result);
    }

    // Find the kth smallest value in all BST values
    // PLAN
    // use inorder traversal and create an array with k
    // values
    // add up to k values and return the k-1 value from the array
    public static int[] vals;
    public static int index = 0;
    public static int findKethSmallestBST(Node root, int k){
        vals = new int[k];
        inOrderDFS(root);
        return vals[k - 1];
    }

    private static void inOrderDFS(Node root){
        if(root == null || index >= vals.length){
            return;
        }

        inOrderDFS(root.left);
        if(index < vals.length){
            vals[index++] = root.val;
        }
        inOrderDFS(root.right);
    }

    // PLAN 2
    // Use a priority queue and add all the values to the queue
    // remove k -1 values from the queue and return the top of the
    // queue
    public static int findKthSmallestBST2(Node root, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        DFS(root, minHeap);
        while(k > 1){
            minHeap.poll();
        }
        return minHeap.poll();
    }

    public static void DFS(Node root, PriorityQueue<Integer> minHeap){
        if(root == null) return;

        minHeap.offer(root.val);
        DFS(root.left, minHeap);
        DFS(root.right, minHeap);
    }
}

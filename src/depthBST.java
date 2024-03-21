public class depthBST {

    public static void main(String[] args){
        Node tree = new Node(1, new Node(9), new Node(20, new Node(15), new Node(7)));
        int resMin = minDepth(tree);
        System.out.println("min depth result: " + resMin);
        int resMax = maxDepth(tree);
        System.out.println("result: " + resMax);

        Node tree2 = new Node(2, null, new Node(3, null, new Node(4, null, new Node(5, null, new Node(6)))));
        resMin = minDepth(tree2);
        System.out.println("min depth result: " + resMin);
        resMax = maxDepth(tree2);
        System.out.println("result: " + resMax);
    }

    // find the max depth of a Binary Tree
    public static int maxDepth(Node root){
        if(root == null){
            return 0;
        }

        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return Math.max(left, right);
    }

    // find the min count of nodes it takes to get from the root
    // to any leaf nodes
    public static int minDepth(Node root){
        if(root == null){
            return 0;
        }

        if(root.left == null){
            return 1 + minDepth(root.right);
        } else if (root.right == null){
            return 1 + minDepth(root.left);
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

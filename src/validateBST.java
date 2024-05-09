import Node.Node;

public class validateBST {

    public static void main(String[] args){
        Node tree = new Node(2, new Node(1), new Node(3));
        boolean result = validateBST(tree);
        System.out.println("Result: " + result);

        tree = new Node(5, new Node(1), new Node(4, new Node(3), new Node(6)));
        result = validateBST(tree);
        System.out.println("Result: " + result);
    }

    // determine whether a tree is a BST where the left subtrees
    // contain node values < their ancestors and right subtrees
    // contain node values > their ancestors
    // PLAN
    // use a helper fn and use a low and high pointer to
    // track the previous ancestor
    // check if
    // 1. root is not null -> return true otherwise
    // 2. root is <= low or root is >= high return false
    // otherwise check if the left subtree && right subtree will return true

    //       5
    // [i] 3 [5]
    //   1   6
    //[i][3]  [3][5]
    //       5
    // [i] 3 [5]
    //   1   2
    //[i][3]  [3][5]
    //
    public static boolean validateBST(Node root){
        return dfsHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean dfsHelper(Node root, int low, int high){
        if(root == null) return true;

        if(root.val <= low || root.val >= high){
            return false;
        }
        return dfsHelper(root.left, low, root.val) && dfsHelper(root.right, root.val, high);
    }
}

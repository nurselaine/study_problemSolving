public class isSubtree {

    public static void main(String[] args){
        Node root = new Node(3, new Node(4, new Node(1), new Node(2)), new Node(5));
        Node subRoot = new Node(4, new Node(1), new Node(2));
        boolean subtree = isSubtree(root, subRoot);
        System.out.println("result: " + subtree);

        root = new Node(1, new Node(1), null);
        subRoot = new Node(1);
        subtree = isSubtree(root, subRoot);
        System.out.println("result: " + subtree);

        root = new Node(1, new Node(1), null);
        subRoot = new Node(1, new Node(2), null);
        subtree = isSubtree(root, subRoot);
        System.out.println("result: " + subtree);
    }

    // declare a global boolean to track whether subtree is found
    // traverse tree via dfs to find first occurence of subRoot
    // check if node and subROot have matching children (call isSameTree)
    // update global boolean
    // assign global boolean with the results of the root's left and right children
    public static boolean isTree = false;
    public static boolean isSubtree(Node root, Node subRoot){
        if(root != null && subRoot == null || root == null && subRoot != null || root == null && subRoot == null){
            return false;
        } else if (subRoot.val == root.val){
            isTree = isSameTree(root, subRoot);
        }

        isTree = isTree || isSameTree(root.left, subRoot);
        isTree = isTree || isSameTree(root.right, subRoot);
        return isTree;
    }

    private static boolean isSameTree(Node p, Node q){
        if (p == null && q == null){
            return true;
        } else if(p != null && q == null || q != null && p == null || p.val != q.val){
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

import Node.Node;

public class isSameTree {

    public static void main(String[] args){
        Node p = new Node(1, new Node(2), new Node(1));
        Node q = new Node(1, new Node(1), new Node(2));

        boolean result  = isSameTree(p, q);
        System.out.println("Is same tree: " + result);

        p = new Node(1, new Node(2), new Node(3));
        q = new Node(1, new Node(2), new Node(3));
        result  = isSameTree(p, q);
        System.out.println("Is same tree: " + result);
    }

    // determine whether two trees are identical
    // PLAN
    // Check if p is null or q is null or p q value do not match thren return false
    // check if p is null AND q is null then return true
    // return whether left AND right subtree are equal
    public static boolean isSameTree(Node p, Node q){
        if(p == null && q == null){
            return true;
        } else if (p == null && q != null || q == null && p != null || p.val != q .val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

import java.util.*;

public class mostFrequentSubtreeSum {
    public static void main(String[] args){
        Node tree = new Node(5, new Node(2), new Node(-3));
//        Map<Integer, Integer> map = new HashMap<>();
//        countSum(tree, map);
//        for(int key : map.keySet()){
//            System.out.println(key);
//        }

        int[] res = mostFrequentSubtreeSum(tree);
        for(int i : res){
            System.out.print(i + " ");
        }

        System.out.println("");
        Node tree2 = new Node(5, new Node(2), new Node(-5));
        res = mostFrequentSubtreeSum(tree2);
        for(int i : res){
            System.out.print(i + " ");
        }
    }

    // find the sum of all subtrees and find the most common
    // subtree sums and find all occurences of each sum
    // return the most common sum and it there are multiple sums with
    // the same max occurences then return all the sums
    // PLAN
    // Use a hasmap to count occurence for each sum
    // Use a helper fn to find all the sums of the tree
    // Find the maximum occurence of the sums in the map
    // add all the sums that occur max times to an array
    // return the array
    public static int[] mostFrequentSubtreeSum(Node root){
        Map<Integer, Integer> map = new HashMap<>();
        countSum(root, map);

        int max = Collections.max(map.values());
        List<Integer> values = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == max){
                values.add(entry.getKey());
            }
        }
        int[] res = new int[values.size()];
        for(int i = 0; i< res.length; i++){
            res[i] = values.get(i);
        }
        return res;
    }

    private static int countSum(Node root, Map<Integer, Integer> map){
        if(root == null){
            return 0;
        }

        int left = countSum(root.left, map);
        int right = countSum(root.right, map);
        int sum = root.val + left + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}

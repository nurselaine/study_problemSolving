import java.util.HashSet;
import java.util.Set;

public class validSudoku {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{new int[]{1, 2, 3}, new int[]{3, 1, 2}, new int[]{2, 3, 1}};
        boolean result = checkValid(matrix);
        System.out.println(result);

        matrix = new int[][]{new int[]{1, 1, 1}, new int[]{1, 2, 3}, new int[]{1, 2, 3}};
        result = checkValid(matrix);
        System.out.println(result);
    }

    /**
     * Given a nxn grid, check if every row and column contains all
     * integers from 1 to n inclusive
     *
     * return true if matrix is valid and false otherwise
     *
     * PLAN
     * create a set with num from 1 to n
     * declare a results boolean
     * iterate from 1 to n
     * create a values set
     * > check if
     * 1. row[] contains all values in set
     * -- for each value, add into set
     * > if value is already in set then return false
     * > if set does not match original set then return false
     * > if set size is not n then return false
     *
     * empty set and restart with col[]
     * 2. col[] contains all values in set
     *
     * return true
     * */
    public static boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= n; i++){
            set.add(i);
        }

        for(int i = 0; i < n; i++){
            Set<Integer> curr = new HashSet<>();

            // get row
            int[] row = matrix[i];

            // get col
            int[] col = new int[n];
            for(int j = 0; j < n; j++){
                col[j] = matrix[j][i];
            }
            boolean validRow = helper(row, curr, set, n);
            boolean validCol = helper(col, curr, set, n);
            if(!validRow || !validCol){
                return false;
            }

        }

        return true;
    }

    private static boolean helper(int[] nums, Set<Integer> set, Set<Integer> oriSet, int n) {
        for(int num : nums){
            if(set.contains(num)){
                return false;
            }
            set.add(num);
        }

        if(set.size() != n || !set.equals(oriSet)){
            return false;
        }
        set.clear();
        return true;
    }
}

package leetcode150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpiralMatrix {

    public static void main(String[] args){
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> res = spiralMatrix(matrix);
        System.out.println(res.toString());
    }

    /**
     * Given a mxn matrix return all elements
     * in spiral order
     *
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     *
     * spiral order: 1 2 3 6 9 8 7 4 5
     * directions of spiral order: right, down, left, up repeat
     *
     * PLAN
     * Initialize a list and a visited set
     * declare a last direction: 3 (0, 1, 2, 3) -> (right, down, left, up)
     * declare a curr direction: 0
     *
     * size -> find nxm value
     * current roww current col
     * recursively do the following
     * check if
     * 1. curr row and curr col are not valid OR curr row and col are in visited STOP
     * 2. curr direction < last direction
     *  - if not then update to 0
     *
     * calculate next position
     * check if:
     * curr direction is RIGHT
     * - call recursive method and update col + 1
     * - when call is over update
     * curr direction is DOWN
     * - call recursive method and update row + 1
     * curr direction is LEFT
     * - call recursive method and update col - 1
     * curr direction is UP
     * - call recursive method and update row - 1
     *
     * Mark the matrix as visited with -111 after visiting
     *
     * Time complexity: O(n*m)
     * Space complexity: O(n*m)
     *
     * */
    public static List<Integer> spiralMatrix(int[][] matrix){
        int SIZE = matrix.length * matrix[0].length;
        int ROW = matrix.length;
        int COL = matrix[0].length;
        int VISITED = -111;
        List<Integer> res = new ArrayList<>();
        int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
        int currDir = 0;
        int row = 0; int col = 0;
        res.add(matrix[row][col]);
        matrix[row][col] = VISITED;

        while(res.size() < SIZE){

            // get next location
            int rowInc = dir[currDir][0]; // 0
            int colInc = dir[currDir][1]; // -1

            if(row + rowInc < 0 || row + rowInc == ROW || col + colInc < 0 || col + colInc == COL
            || matrix[row + rowInc][col + colInc] == VISITED){
                currDir++; // 2
                if(currDir > 3) currDir = 0;
            } else {
                // res: 1, 2, 3, 6, 9, 8
                row += rowInc;
                col += colInc;
                res.add(matrix[row][col]);
                matrix[row][col] = VISITED;
            }

        }
        return res;
    }
}

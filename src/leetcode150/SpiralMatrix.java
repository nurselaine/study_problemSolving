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
     * */
    public static List<Integer> spiralMatrix(int[][] matrix){
        int SIZE = matrix.length * matrix[0].length;
        int ROW = matrix.length;
        int COL = matrix[0].length;
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int currDir = 0;
        int row = 0; int col = 0;
        while(res.size() < SIZE){
            int index = (row * COL) + col; // 2
            int value = matrix[row][col]; // 3
            if(visited.contains(index)){
                currDir++; // 1
            } else {
                res.add(value); // 1 2 3
                visited.add(index); // 0 1 2
            }

            if(currDir > 3){
                row++;
                currDir = 0;
            }

            if(currDir == 0){
                col += 1; // 0 3
            } else if (currDir == 1){
                row += 1;
            } else if (currDir == 2){
                col -= 1;
            } else if (currDir == 3){
                row -= 1;
            }

            if(col == COL){
                col--; // 0 2
            } else if (col < 0){
                col++;
            } else if (row == ROW){
                row--;
            } else if (row < 0){
                row++;
            }

        }
        return res;
    }

    private static void spiralMatrix(int[][] matrix, List<Integer> res, Set<Integer> visited, int dir, int row, int col, int SIZE){
        int index = (row * matrix[0].length) + col;
        if( row < 0 || row >= matrix.length || col < 0 || col > matrix[0].length){
            return;
        } else if (res.size() == SIZE - 1){
            return;
        } else if (visited.contains(index)){

        }


    }
}

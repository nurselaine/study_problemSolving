package leetcode150;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpiralOrder {

    public static void main(String[] args){

    }

    public List<Integer> spiralOrder(int[][] matrix) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> spiralOrder = new ArrayList<>();
        int ROW = matrix.length;
        int COL = matrix[0].length;
        spiralOrder(matrix, 0, 0, visited, spiralOrder, ROW, COL);
        return spiralOrder;

    }

    private void spiralOrder(int[][] matrix, int row, int col, Set<Integer>visited, List<Integer> spiralOrder, int ROW, int COL){
        int index = (COL * row) + col;
        if(row >= ROW || row < 0 || col >= COL || col < 0 || visited.contains(index)){
            return;
        }
        visited.add(index);
        spiralOrder.add(matrix[row][col]);
        // right col + 1
        spiralOrder(matrix, row, col + 1, visited, spiralOrder, ROW, COL);
        // down row + 1
        spiralOrder(matrix, row + 1, col, visited, spiralOrder, ROW, COL);
        // left col - 1
        spiralOrder(matrix, row, col - 1, visited, spiralOrder, ROW, COL);
        // up row + 1
        spiralOrder(matrix, row - 1, col, visited, spiralOrder, ROW, COL);
    }
}

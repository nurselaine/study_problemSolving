package leetcode150;

import java.util.Arrays;
import java.util.HashMap;

public class DP {

    public static void main(String[] args){
        minCostClimbingStairsTest();
    }

    public static void climbingStairsTest(){
        int n = 44;
        System.out.println(climbingStairs(n));
    }

    public static void minCostClimbingStairsTest(){
        int[] cost = new int[]{10, 15, 20};
//        System.out.println(minCostClimbingStairs(cost));
        System.out.println(minCostClimbingStairsTabulation(cost));
    }

    /**
     * given an int N steps, return the number of
     * step combinations (unique) that can be taken from
     * step 0 to step n
     *
     * recursively climb up from 0 to n
     * check either 1 step or 2 step
     * check if :
     * - current step == n then incre global value
     * - current step > n then stop
     * */
    public static int[] memo;
    public static int climbingStairs(int n){
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return climb(n, 0);
    }

    private static int climb(int n, int curr){
        if (curr > n){
            return 0;
        }
        if(memo[curr] != -1){
            return memo[curr];
        } else if(curr == n){
            return 1;
        }

        memo[curr] = climb(n, curr + 1) + climb(n, curr + 2);
        return memo[curr];
    }

    /**
     * Given an int array where cost[i] is the cost of the ith step
     * you can take 1 or 2 steps
     *
     * return the min cost to get to the top of the stairs
     * total steps : array length
     *
     * PLAN
     * use a memo int array to save cost amount of the indices
     * that lead to the top
     * create a global mincost variable to update the min cost
     * recursively step up using 1 or 2 steps
     * check if
     * - curr step is == n then update memo with current cost, update min cost and return 1
     * - curr step > n then update memo with -1
     * - memo[curr step] != -1 then return memo[curr step]
     *
     * update memo with sum of recursive 1 step and 2 step cost
     * return memo
     * */

    private static HashMap<Integer, Integer> table;
    public static int minCostClimbingStairs(int[] cost){
        table = new HashMap<>();

        return minCostClimbingStairs(cost.length, cost);
    }

    // 10 15 20
    // 10
    // 0
    // -1 2 -1 -1
    public static int minCostClimbingStairs(int curr, int[] cost){
        if(curr <= 1){
            return 0;
        }

        if(table.containsKey(curr)){
            return table.get(curr);
        }

        int stepOne = cost[curr - 1] + minCostClimbingStairs(curr - 1, cost);
        int stepTwo = cost[curr - 2] + minCostClimbingStairs(curr - 2, cost);
        table.put(curr, Math.min(stepOne, stepTwo));
        return table.get(curr);
    }

    public static int minCostClimbingStairsTabulation(int[] cost){
        int[] memo = new int[cost.length + 1];

        for(int i = 2; i < memo.length; i++){
            int stepOne = cost[i - 1] + memo[i - 1];
            int stepTwo = cost[i - 2] + memo[i - 2];
            memo[i] = Math.min(stepOne, stepTwo);
        }
        return memo[memo.length - 1];
    }
}

package leetcode150;

import java.util.Arrays;

public class DP {

    public static void main(String[] args){
        climbingStairsTest();
    }

    public static void climbingStairsTest(){
        int n = 44;
        System.out.println(climbingStairs(n));
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
}

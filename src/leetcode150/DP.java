package leetcode150;

public class DP {

    public static void main(String[] args){
        climbingStairsTest();
    }

    public static void climbingStairsTest(){
        int n = 3;
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
    public static int climbingStairs = 0;
    public static int[] memo;
    public static int climbingStairs(int n){
        climb(n, 0);
        return climbingStairs;
    }

    private static void climb(int n, int curr){
        if (curr == n){
            climbingStairs++;
        } else if (curr > n){
            return;
        }

        climb(n, curr + 1);
        climb(n, curr + 2);
    }
}

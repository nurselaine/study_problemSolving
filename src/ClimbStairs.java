import java.util.Arrays;

public class ClimbStairs {

    public static int[] memo;
    public static void main(String[] args){
        int result = climbingStairs(4);
        System.out.println(result);
    }

    // Exhaustive search: determine the total number of ways to
    // climb n steps of stairs by taking either 1 or 2 steps
    // PLAN
    // Use a helper fn
    // check if current step [i] is >= n
    // - if so then return 1
    // otherwise
    // return fn call to results of taking 1 step + results of taking 2 step
    public static int climbingStairs(int n){
        return helper(n, 0);
    }

    private static int helper(int n, int index){
        if(index >= n){
            return 1;
        }

        return helper(n, index + 1) + helper(n, index + 2);
    }

    // optimized
    // use memoization
    // record the results of each step i in a memoization array
    // return the results of the memo array [0] after iterating over all the steps
    // if the index in the array has been populated then return the value of the memo
    public static int climbingStairsMemo(int n){
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return memoHelper(n, 0);
    }

    private static int memoHelper(int n, int index){
        if(index >= n){
            return 1;
        }

        if(memo[index] != -1){
            return memo[index];
        }
        memo[index] = memoHelper(n, index + 1) + memoHelper(n, index + 2);
        return memo[index];
    }
}


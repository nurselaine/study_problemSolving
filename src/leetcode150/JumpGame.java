package leetcode150;

import java.util.Arrays;

public class JumpGame {

    public static void main(String[] args){
        int[] nums = new int[]{2,3,1,1,4};
//        boolean result = optimizedCanJump(nums);
//        System.out.println(result);
//
//        result = bottomUpCanJump(nums);
//        System.out.println(result);
//
//        result  = greedyCanJump(nums);
//        System.out.println(result);

        int res = jump(nums);
        System.out.println(res);

    }

    /**
     * Given an array, each element represents a MAX
     * number of steps that can be taken from the current
     * index
     * Goal: reach the end of the array
     *
     * PLAN
     * declare a jump counter
     * iterate over array
     * check if
     * 1. jump counter + current index > array size - return true
     * otherwise
     * declare a current jump count
     * while jump counter > 0
     * update index and current jump count to next index max jump
     * check if
     * 1. current jump > array size - return true
     * 2. current jump count == 0 - decrement jump count and update current jump to array[i + jump count]
     *
     * if jump count == 0 then return false
     * */
//    public static boolean canJump(int[] nums){
//        int N = nums.length;
//        int jump = nums[0];
//        for(int i = 0; i < N; i++){
//
//            if(i + jump >= N - 1){
//                return true;
//            }
//
//            int curr = jump;
//            int maxJump = nums[i];
//            int currIndex = i + maxJump;
//            while(jump > 0){
//
//                if(currIndex < N){
//                    maxJump = nums[currIndex];
//                } else if (maxJump <= 0) return false;
//            }
//
//        }
//    }

    public static boolean canJump(int[] nums){
        return canJumpH(nums, 0);
    }

    private static boolean canJumpH(int[] nums, int index){
        if(index == nums.length - 1){
            return true;
        }

        int maxJump = Math.min(index + nums[index], nums.length - 1);
        for(int i = index + 1; i <= maxJump; i++){
            if(canJumpH(nums, i)){
                return true;
            }
        }
        return false;
    }

    /**
     * Optimizing above solution
     *
     * Top-down DP (optimized backtracking)
     *
     * Use a memo array with values: good, bad, unknown
     * PLAN
     * base case
     * 1. check if memo array is known
     * - if so then return T/F whether it is known or not known
     * then find the maxJump value
     * iterate from the maxJump down to current index
     * check if
     * 1. current index returns true - update memo array [i] with good and return true
     *
     * otherwise
     * update memo array at index and return false
     * */

    public static char[] memo;
    public static boolean optimizedCanJump(int[] nums){
        memo = new char[nums.length];
        Arrays.fill(memo, 'u');
        memo[nums.length-1] = 't';
        return optimizedCanJump(nums, 0);
    }

    private static boolean optimizedCanJump(int[] nums, int index){
        if(memo[index] != 'u'){
            return memo[index] == 't' ? true : false;
        }

        int maxJump = Math.min(index + nums[index], nums.length - 1);
        for(int i = index + 1; i <= maxJump; i++){
            if(optimizedCanJump(nums, i)){
                memo[index] = 't';
                return true;
            }
        }
        memo[index] = 'f';
        return false;
    }

    /**
     * start from the right of the array so when we query a position
     * to the right, it will already have a t/f in the memo table
     *
     * PLAN
     * declare a memo array set all to 'u' for unknown
     * except the last index which we know is 't'
     *
     * iterate from right to left of nums
     * find the maxJump value
     * then iterate from current index + 1 to maxJump
     * */
    public static boolean bottomUpCanJump(int[] nums){
        char[] memo = new char[nums.length];
        Arrays.fill(memo, 'u');
        memo[memo.length - 1] = 'g';

        for(int i = nums.length - 2; i >= 0; i--){
            int maxJump = Math.min(i + nums[i], nums.length - 1);
            for(int j = i + 1; j <= maxJump; j++){
                if(memo[j] == 'g'){
                    memo[i] = 'g';
                    break;
                }
            }
        }

        return memo[0] == 'g';
    }

    /**
     * Greedy
     * check if
     * 1. index + nums[index] >= leftmostGoodIndex
     * left most good index is the smallest index that leads to
     * the last index
     * as long as we can get to or > then this position then we
     * can return true without iterating over the entire array
     *
     * PLAN
     * declare a lastIndex as last index of nums
     * iterate over nums
     * check if
     * 1. i + nums[i] >= lastIndex
     * - update lastIndex to i
     * return whether lastIndex is equal to 0
     * */
    public static boolean greedyCanJump(int[] nums){
        int lastIndex = nums.length - 1;
        for(int i = nums.length - 1; i >= 0; i--){
            if(i + nums[i] >= lastIndex){
                lastIndex = i;
            }
        }
        return lastIndex == 0;
    }


    /**
     * Jump Game II
     * given an array, initially positioned at 0
     *
     * return the minimum # of jumps it takes to get to nums[n - 1]
     *
     * PLAN
     * - backtracking
     * check if index == n - 1 - return counter
     *
     * calculate max jump value between n-1 OR i + nums[i]
     * return Max between current maxJump AND maxJump - 1
     * */
    public static int minJumps = Integer.MAX_VALUE;
    public static int jump(int[] nums){
        jump(nums, 0, 0);
        return minJumps;
    }

    public static boolean jump(int[] nums, int index, int jumps){
        if(index == nums.length - 1){
            minJumps = Math.min(minJumps, jumps);
            return true;
        }

        int maxJump = Math.min(nums.length - 1, index + nums[index]);
        for(int i = index + 1; i < maxJump; i++){
            if(jump(nums, i + index, jumps + 1)){
                return true;
            }
        }

        return false;
    }
}

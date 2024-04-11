package leetcode150;

public class JumpGame {

    public static void main(String[] args){
        int[] nums = new int[]{2,3,1,1,4};
        boolean result = canJump(nums);
        System.out.println(result);
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
}

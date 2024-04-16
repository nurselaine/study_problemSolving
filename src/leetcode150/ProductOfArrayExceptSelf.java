package leetcode150;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args){
        int[] nums = new int[]{1, 2, 3, 4};
//        nums = productExceptSelf(nums);
        nums = productExceptSelfII(nums);
        for(int n : nums){
            System.out.println(n + " ");
        }
    }

    /**
     * Given an integer, return an array such that arr[i]
     * is equal to the product of all the elements of nums except
     * nums[i]
     * arr : 1 2 3 4
     * var v = 1, 2, 6
     * 1 2 3 4
     * arr[1] : 1 * 1 = 1
     * arr[2] : 2 * 1 = 2
     * arr[3] : 3 * 2 = 6
     * hArr : 1 1 2 6
     * fArr : 24 12 4 1
     *
     * v = 1, 4
     * hArr[i] * arr[i + 1]
     * > v = 1 * 1
     * arr[3] : 6 * 1 = 6
     * > v = 1 * 4
     * arr[2] : 2 * 4 = 8
     * > v = 4 * 3
     * arr[1] : 1 * 12 = 12
     * > v = 12 * 2
     * arr[0] : 1 * 24 = 24
     * PLAN
     * declare a var to keep the prev product
     *
     * */
    public static int[] productExceptSelf(int[] nums){
        // 1 2 3 4
        // 1  1  2 6 - prefix
        // 24 12 4 1 - suffix
        // 24 12 8 6
        int prefix = 1;
        int[] prefixArr = new int[nums.length];
        int[] suffixArr = new int[nums.length];
        Arrays.fill(prefixArr, 1);
        Arrays.fill(suffixArr, 1);

        for(int i = 1; i < nums.length; i++){
            prefixArr[i] = prefix * nums[i - 1];
            prefix = prefixArr[i];
        }

        prefix = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            suffixArr[i] = prefix * nums[i + 1]; //
            prefix = suffixArr[i]; // 4
        }

        for(int i = 0; i < nums.length; i++){
            nums[i] = suffixArr[i] * prefixArr[i];
        }

        return nums;

    }


    /**
     * PLAN do not use extra space
     * declare a prefix variable to hold the
     * */
    public static int[] productExceptSelfII(int[] nums){
        int[] prefixArr = new int[nums.length];
        prefixArr[0] = 1;

        int prefix =1;
        for(int i = 1; i < nums.length; i++){
            prefixArr[i] = prefix * nums[i - 1];
            prefix = prefixArr[i];
        }

        prefix = 1;
        // 1 1 2 6
        // 1 2 3 4
        //     8 6
        for(int i = nums.length - 1; i >= 0; i--){ // 0
            int next = nums[i]; // 1
            nums[i] = prefix * prefixArr[i]; // 24 * 1 = 24
            prefix *= next; // 12 * 2 = 24
        }
        return nums;
    }
}

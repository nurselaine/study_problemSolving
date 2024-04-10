package leetcode150;

import java.util.Arrays;

public class Day2 {
    public static void main(String[] args){

    }


    /**
     * Majority element of an array is on who's frequency
     * occurs more than n/2 times
     *
     * PLAN
     * sort array
     * return the element at n/2 index
     * */
    public static int majorityElement(int[] nums){
        Arrays.sort(nums);
        return nums[(nums.length - 1) / 2];
    }

    /**
     *
     * */
    public static void rotateArray(int[] nums){

    }
}

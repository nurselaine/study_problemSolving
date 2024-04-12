package leetcode150;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

public class hIndex {

    public static void main(String[] args){
        int[] nums = new int[]{3, 0, 6, 1, 5};
        int result = hIndex(nums);
        System.out.println(result);

        nums = new int[]{1, 3, 1};
        result = hIndex(nums);
        System.out.println(result);
    }

    /**
     * Given an array, find the h-index where h is within 1-N and
     * there are h total elements that have values >= h
     *
     * Finding pattern h where h represents the total elements
     * that are greater than or equal to h
     *
     * PLAN
     * sort in descending orrder
     *
     * declare h counter and an index counter
     * iterate over sorted array
     * each index increment h counter
     * check if:
     * 2. element >= h AND index <= h - increment by 1
     * 1. element < h AND index >= h - return h
     *
     * if index >= h
     * return nums count
     * */
    public static int hIndex(int[] nums){

        Arrays.sort(nums);

        int h = 0;
        int index = 1;
        for(int i = nums.length - 1; i >= 0; i--){
            if(index >= h) {
                if (nums[i] > h) {
                    h++;
                } else if (nums[i] >= h && index == h) {
                    return h;
                }
            }
            index++;
        }

        return h;
    }

    public static int refactorHIndex(int[] nums){
        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++){
            if(nums.length - i <= nums[i]){
                return nums.length - i;
            }
        }
        return 0;
    }

}

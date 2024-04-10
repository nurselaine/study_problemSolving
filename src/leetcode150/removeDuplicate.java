package leetcode150;

public class removeDuplicate {

    public static void main(String[] args){
//        int[] nums = new int[]{1, 1, 2};
//        int result = removeDuplicates(nums);
//        System.out.println(result);
//        for(int n : nums){
//            System.out.print(n + " ");
//        }
//
//        System.out.println("");
//        nums = new int[]{0,0,1,1,1,2,2,3,3,4};
//        result = removeDuplicates(nums);
//        System.out.println(result);
//        for(int n : nums){
//            System.out.print(n + " ");
//        }

        int[] nums2 = new int[]{1,1,1,2,2,3};
        int  result2 = removeDuplicatesII(nums2);
        System.out.println(result2);
        for(int n : nums2){
            System.out.print(n + " ");
        }
    }

    /**
     * given a sorted array, remove duplicate elements in place
     * each unqiue element appears once and the relative order of elements
     * should be kept the same
     *
     * PLAN
     * Use an index counter
     * iterate over nums
     * if current element != prev element
     * update nums[index] to the current element
     * return the index value
     * */
    public static int removeDuplicates(int[] nums){ // 1 1 2
        int index = 1;
        for(int i = 1; i < nums.length; i++){ // 2
            if(nums[i] != nums[i - 1]){ //
                nums[index++] = nums[i]; // 1 2 2
            }
        }
        return index;
    }


    /**
     * given a sorted array, remove duplicates so each unique element
     * appears at most twice
     *
     * PLAN
     * declare index counter and frequency counter
     * iterate over nums
     * check if:
     * 1. num[i] != nums[i + 1]
     * - reset counter to 1 & update nums[index] to nums[i]
     * 2. freq counter is < 2
     * - increment counter & update nums[index] to nums[i]
     * */
    public static int removeDuplicatesII(int[] nums){ // 1 1 1 2 2 3
        int freq = 1;
        int index = 1;
        for(int i = 1; i < nums.length; i++){ // 5
            if(nums[i] != nums[i - 1]){ //
                freq = 1;
                nums[index++] = nums[i]; // 1 1 2 2 2 3
            } else if (freq < 2){
                nums[index++] = nums[i]; // 1 1 2 2 2 3
                freq++; // 3
            }
        }
        return index;
    }
}

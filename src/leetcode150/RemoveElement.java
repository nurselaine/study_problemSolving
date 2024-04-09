package leetcode150;

public class RemoveElement {

    public static void main(String[] args){
        int[] nums = new int[]{3, 2, 2, 3};
//        int result = removeElement(nums, 3);
        int result = removeElementSolution(nums,3);
        System.out.println(result);
        for(int n : nums){
            System.out.print(n + " ");
        }
        System.out.println("");
        nums = new int[]{0,1,2,2,3,0,4,2};
//        result = removeElement(nums, 2);
        result = removeElementSolution(nums,2);
        System.out.println(result);
        for(int n : nums){
            System.out.print(n + " ");
        }
    }

    /**
     * Remove all occurences of val in nums in-place
     * then return the number of elements that are not == to val
     * remove any gaps in the array where val use to be
     *
     * similar to arraylist method
     * PLAN
     * declare a val counter & nums length val
     * iterate over nums
     * check if element is == to val
     * 1. if it is then
     *  iterate over elements in nums from current index to nums length
     *  use a temp var to hold the element that is i + 1 from val and reassign i to i + 1
     *  repeat until reaching nums length
     *  decrement nums length each time
     * */
    public static int removeElement(int[] nums, int val){
        int length = nums.length - 1;
        int counter = 0;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == val){
                int j = i;
                while(j < length){
                    nums[j] = nums[j + 1];
                    j++;
                }
                nums[j] = 0;
                counter++;
                length--;
                i--;
            }
        }

        return counter;
    }

    /**
     * Using exclusion to move non-val elements to left of array
     * and overwriting elements that are == val
     * */
    public static int removeElementSolution(int[] nums, int val){
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[index++] = nums[i];
            }
        }
        return index;
    }
}

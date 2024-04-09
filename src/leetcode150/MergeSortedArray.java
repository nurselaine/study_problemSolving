package leetcode150;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args){
        int[] num1 = new int[]{1, 2, 3, 0,0,0};
        int[] num2 = new int[]{2, 5, 6};
        merge(num1, num2, 3, 3);
        for(int n : num1){
            System.out.print(n + " ");
        }
    }

    /**
     * Given 2 int arrays, sorted in non-decreasing order and 2 integers
     * representing length of each array - merge nums1 and nums2 into a single array
     * the final sorted array should be stored in array nums1 which should have the length
     * m + n
     *
     * PLAN
     * Copy contents of array1 into a new array1 that only has the first
     * m digits copied over
     * create 2 pointers for newly created array
     * traverse over original array1 and check if
     * 1. copy1 val < array2 val
     * - overwrite copy val to array1
     * 2. copy1 val > array2 val
     * - overwrite array2 val to array1
     *
     * return array1
     * */
    public static void merge(int[] nums1, int[] nums2, int m, int n){
        int[] copy1 = Arrays.copyOf(nums1, m);

        int p1 = 0;
        int p2 = 0;
        int index = 0;
        while(p1 < m && p2 < n){
            if(copy1[p1] <= nums2[p2]){
                nums1[index++] = copy1[p1++];
            } else {
                nums1[index++] = nums2[p2++];
            }
        }

        if (p1 < m) {
            System.arraycopy(copy1, p1, nums1, index, m - p1);
        } else if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, index, n - p2);
        }
    }

    // use an arraylist and convert it back to int array
}
